- daemon=true  
- include_dbs=ali   
- 可排除库、表、过滤掉某些行。也可用一段js灵活处理数据   
    如 exclude: test_maxwell.user_info.userid = 1 排除test_maxwell库user_info表userid值为1的行
 --filter='exclude: *.*,include:test_maxwell.user_info,exclude: test_maxwell.user_info.userid = 1' 
 
 --metrics_type=http \
 --metrics_jvm=true \
 --http_bind_address=node2 \
 --http_port=8090 \
 --http_path_prefix=db_test_maxwell
 
 --include_dbs表示要筛选具体的数据库 --include_tables表示筛选具体库下的具体表


gtid_mode
如果 mysql server 启用了GTID，maxwell也可以基于gtid取event。如果mysql server发生failover，maxwell不需要手动指定newfile:postion


init_position
手动指定maxwell要从哪个binlog，哪个位置开始。指定的格式FILE:POSITION:HEARTBEAT。只支持在启动maxwell的命令指定，比如 --init_postion=mysql-bin.0000456:4:0。


kafka和kenesis都支持分区，可以选择根据 database, table, primary_key, 或者column的值去做partition
maxwell默认使用database，在启动的时候会去检查是否topic是否有足够多数量的partitions，所以要提前创建好
producer_partition_by=database