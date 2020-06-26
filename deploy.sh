#!/bin/sh
username=okrm
remote_id=121.40.244.200
remote_dir=/home/okrm/servicespace/okrm-server
fileName=useful-person.okrm-server-0.0.1-SNAPSHOT.jar
time=$(date "+%Y-%m-%d_%H:%M:%S")
logFile=deploy_${time}.log
bakFile=${fileName}.${time}
logFn() {
    echo "$(date "+%Y-%m-%d %H:%M:%S") $1" >> ${logFile}
}
# 构建
logFn "构建"
mvn clean install package -Dmaven.test.skip=true -Pprod
logFn "备份"
ssh ${username}@${remote_id} > /dev/null 2>&1 << EOF
sh /home/okrm/goaloneService.sh okrm-server clean
exit
EOF

# 拷贝文件
logFn "发布"
scp useful-person.okrm-server/target/${fileName} okrm@okrm:${remote_dir}/${fileName}

logFn "启动"
ssh ${username}@${remote_id} > /dev/null 2>&1 << EOF
sh /home/okrm/goaloneService.sh okrm-server prod start
exit
EOF
echo done!