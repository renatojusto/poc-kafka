# Criando novo tópico
docker-compose exec kafka kafka-topics --create --topic PEDIDO-NOVO-PEDIDO --partitions 1 --replication-factor 1 --if-not-exists --zookeeper zookeeper:2181

# Verificando se o tópico foi criado
docker-compose exec kafka kafka-topics --describe --topic PEDIDO_NOVO_PEDIDO --zookeeper zookeeper:2181

# Enviando mensagens para o tópico
docker-compose exec kafka bash -c "seq 100 | kafka-console-producer --request-required-acks 1 --broker-list localhost:9092 --topic PEDIDO_NOVO_PEDIDO && echo 'Produced 100 messages.'"

# Consumindo mensagens
docker-compose exec kafka kafka-console-consumer --bootstrap-server localhost:9092 --topic PEDIDO_NOVO_PEDIDO --from-beginning --max-messages 100

# Aumentando o número de partições
docker-compose exec kafka kafka-topics --bootstrap-server localhost:9092 --alter --topic PEDIDO_NOVO_PEDIDO --partitions 3

# Verificando os grupos de consumo
docker-compose exec kafka kafka-consumer-groups --all-groups --bootstrap-server localhost:9092 --describe
