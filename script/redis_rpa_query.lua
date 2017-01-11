local rpa_id_list = redis.call('ZRANGE','rpa:id',0,-1);
for k,v in ipairs(rpa_id_list) do
    local hash_rpa_en_key = 'rpa:entity:'..v
    if redis.call('EXISTS',hash_rpa_en_key) then
      local hash_rpa_en_name = redis.call('HGET',hash_rpa_en_key,'username')
      local hash_rpa_en_date = redis.call('HGET',hash_rpa_en_key,'authdate')
      local z_user_key = 'rpa:user:'..hash_rpa_en_name
      local z_date_key = 'rpa:date'
      redis.call('ZADD',z_user_key,tonumber(hash_rpa_en_date),v)
      redis.call('ZADD',z_date_key,tonumber(hash_rpa_en_date),v)     
    end
end
return ok
