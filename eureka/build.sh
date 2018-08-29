#!/usr/bin/env bash
mvn clean package -Dmaven.test.skip=true -U

docker build -t springcloud/euraka .

docker tag springcloud/euraka registry.cn-qingdao.aliyuncs.com/springcloud-demo/eureka:0.0.1

docker push registry.cn-qingdao.aliyuncs.com/springcloud-demo/eureka:0.0.1
