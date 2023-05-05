FROM rabbitmq

RUN rabbitmq-plugins enable --offline rabbitmq_management

EXPOSE 15671 15672 15692 25672 5672
