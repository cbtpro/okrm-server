read -p "请输入spring.profiles.active：" profile
read -s -p "请输入密钥：" key
echo
read -s -p "请输入密码：" password
echo
mvn jasypt:decrypt-value \
	-Djasypt.encryptor.password=$key \
	-Djasypt.plugin.value=$password \
	-Dmaven.test.skip=true \
	-Dspring.profiles.active=$profile