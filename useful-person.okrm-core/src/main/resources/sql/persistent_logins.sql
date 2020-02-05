# drop table if exists persistent_logins; #如果persistent_logins表存在就删除
# 如果不存在表persistent_logins就新建这张表
CREATE TABLE IF NOT EXISTS persistent_logins (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) PRIMARY KEY,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL
);