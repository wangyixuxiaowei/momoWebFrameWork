#!/bin/bash
mysql_host=172.16.31.182
mysql_user=root
mysql_pwd=rootwx
database=radius
function gen_sql(){
  query="SELECT CONCAT(\
    '*4\r\n',\
    '$', LENGTH(redis_cmd), '\r\n',redis_cmd, '\r\n',\
    '$', LENGTH(redis_key), '\r\n',redis_key, '\r\n',\
    '$', LENGTH(score), '\r\n',score,'\r\n',\
    '$', LENGTH(member), '\r\n', member, '\r'\
  )\
  FROM (\
    SELECT\
    'ZADD' AS redis_cmd, 'rpa:id' AS redis_key,\
    0 AS score,id AS member\
    FROM (select id from radpostauth limit 1,1000000) AS B\
  ) AS T"
  echo $query
}
mo=$(gen_sql)
echo $mo | mysql -u$mysql_user -p$mysql_pwd -h$mysql_host $database --skip-column-names --raw | redis-cli --pipe
