#!/bin/bash
mysql_host=172.16.31.182
mysql_user=root
mysql_pwd=rootwx
database=radius
function gen_sql(){
  mysql2redis="SELECT CONCAT(\
    '*10\r\n',\
    '$', LENGTH(redis_cmd), '\r\n',redis_cmd, '\r\n',\
    '$', LENGTH(redis_key), '\r\n',redis_key, '\r\n',\
    '$', LENGTH(hkey1), '\r\n', hkey1, '\r\n',\
    '$', LENGTH(hval1), '\r\n', hval1, '\r\n',\
    '$', LENGTH(hkey2), '\r\n', hkey2, '\r\n',\
    '$', LENGTH(hval2), '\r\n', hval2, '\r\n',\
    '$', LENGTH(hkey3), '\r\n', hkey3, '\r\n',\
    '$', LENGTH(hval3), '\r\n', hval3, '\r\n',\
    '$', LENGTH(hkey4), '\r\n', hkey4, '\r\n',\
    '$', LENGTH(hval4), '\r\n', hval4, '\r'\
  )\
  FROM (\
    SELECT\
    'HMSET' AS redis_cmd, CONCAT('rpa:entity:',id) AS redis_key,\
    'username' AS hkey1, username AS hval1,\
    'pass' AS hkey2, pass AS hval2,\
    'reply' AS hkey3, reply AS hval3,\
    'authdate' AS hkey4, DATE_FORMAT(authdate,'%Y%m%d%H%i%S') AS hval4\
    FROM (select id,username,pass,reply,authdate from radpostauth limit 1,1000000) AS B\
  ) AS T"
  echo $mysql2redis
}
mo=$(gen_sql)
echo $mo | mysql -u$mysql_user -p$mysql_pwd -h$mysql_host $database --skip-column-names --raw | redis-cli --pipe
