# kogito-knative-loan

## Start services locally

  * start eligibility service
    ```sh
    cd eligibility
     mvn clean compile quarkus:dev
    ```
  * start notation service
    ```sh
    cd ../notation
     mvn clean compile quarkus:dev
    ```
  * start loan service
    ```sh
    cd ../loan
     mvn clean compile quarkus:dev
    ```

  * call the eligibility service
  ```json
      curl -X POST \
      -H "content-type: application/json"  \
      -H "ce-specversion: 1.0"  \
      -H "ce-source: /from/localhost"  \
      -H "ce-type: eligibilityapplication" \
      -H "ce-id: 12346"  \
      -d "{\"age\":3,\"amount\":50000,\"bilan\":{\"gg\":5,\"ga\":2,\"hp\":1,\"hq\":2,\"dl\":50,\"ee\":2,\"siren\":\"423646512\",\"variables\":[]},\"ca\":200000,\"eligible\":false,\"msg\":\"string\",\"nbEmployees\":10,\"notation\":{\"decoupageSectoriel\":0,\"note\":\"string\",\"orientation\":\"string\",\"score\":0,\"typeAiguillage\":\"string\"},\"publicSupport\":true,\"siren\":\"423646512\",\"typeProjet\":\"IRD\"}" http://localhost:8580
  ```

  The eligibility process is triggered 
  ```log
        --/ __ \/ / / / _ | / _ \/ //_/ / / / __/ 
    -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \   
    --\___\_\____/_/ |_/_/|_/_/|_|\____/___/   
    2021-06-08 16:48:49,911 WARN  [io.sma.rea.mes.provider] (Quarkus Main Thread) SRMSG00207: Some components are not connected to either downstream consumers or upstream producers:
      - SubscriberMethod{method:'org.kie.kogito.addon.cloudevents.quarkus.QuarkusCloudEventPublisher#onEvent', incoming:'kogito_incoming_stream'} has no upstream

    2021-06-08 16:48:49,922 INFO  [org.kie.kog.ser.eve.imp.AbstractMessageConsumer] (Quarkus Main Thread) Consumer for class org.redhat.bbank.model.Loan started.
    2021-06-08 16:48:49,923 INFO  [org.kie.kog.add.clo.qua.QuarkusKogitoExtensionInitializer] (Quarkus Main Thread) Registered Kogito CloudEvent extension
    2021-06-08 16:48:49,943 INFO  [io.quarkus] (Quarkus Main Thread) eligibility 2.0.0-SNAPSHOT on JVM (powered by Quarkus 1.13.3.Final) started in 1.334s. Listening on: http://localhost:8580
    2021-06-08 16:48:49,943 INFO  [io.quarkus] (Quarkus Main Thread) Profile dev activated. Live Coding activated.
    2021-06-08 16:48:49,943 INFO  [io.quarkus] (Quarkus Main Thread) Installed features: [cdi, hibernate-validator, kogito-decisions, kogito-predictions, kogito-processes, kogito-rules, mutiny, qute, rest-client, resteasy, resteasy-jackson, servlet, smallrye-context-propagation, smallrye-health, smallrye-openapi, smallrye-reactive-messaging, swagger-ui, vertx, vertx-web]
    2021-06-08 16:48:49,944 INFO  [io.qua.dep.dev.RuntimeUpdatesProcessor] (vert.x-worker-thread-0) Hot replace total time: 1.399s 
    Incoming request :  Loan [age=3, amount=50000.0, bilan=Bilan [dl=50.0, ee=2.0, fl=0.0, fm=0.0, ga=2.0, gg=5.0, hn=0.0, hp=1.0, hq=2.0, siren=423646512], ca=200000.0, elligible=false, msg=string, nbEmployees=10.0, notation=Notation [decoupageSectoriel=0.0, note=string, orientation=string, score=0.0, typeAiguillage=string], publicSupport=true, siren=423646512, typeProjet=IRD, rate=0.0, nbmonths=0]
    Company number : 423646512exist ? true
  ```

  followed by the notation service 
  ```log
        --/ __ \/ / / / _ | / _ \/ //_/ / / / __/ 
    -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \   
    --\___\_\____/_/ |_/_/|_/_/|_|\____/___/   
    2021-06-08 16:48:52,917 WARN  [io.sma.rea.mes.provider] (Quarkus Main Thread) SRMSG00207: Some components are not connected to either downstream consumers or upstream producers:
      - SubscriberMethod{method:'org.kie.kogito.addon.cloudevents.quarkus.QuarkusCloudEventPublisher#onEvent', incoming:'kogito_incoming_stream'} has no upstream

    2021-06-08 16:48:52,927 INFO  [org.kie.kog.ser.eve.imp.AbstractMessageConsumer] (Quarkus Main Thread) Consumer for class org.redhat.bbank.model.Loan started.
    2021-06-08 16:48:52,934 INFO  [org.kie.kog.add.clo.qua.QuarkusKogitoExtensionInitializer] (Quarkus Main Thread) Registered Kogito CloudEvent extension
    2021-06-08 16:48:52,943 INFO  [io.quarkus] (Quarkus Main Thread) notation 2.0.0-SNAPSHOT on JVM (powered by Quarkus 1.13.3.Final) started in 1.762s. Listening on: http://localhost:8680
    2021-06-08 16:48:52,944 INFO  [io.quarkus] (Quarkus Main Thread) Profile dev activated. Live Coding activated.
    2021-06-08 16:48:52,944 INFO  [io.quarkus] (Quarkus Main Thread) Installed features: [cdi, hibernate-validator, kogito-decisions, kogito-predictions, kogito-processes, kogito-rules, mutiny, qute, rest-client, resteasy, resteasy-jackson, servlet, smallrye-context-propagation, smallrye-health, smallrye-openapi, smallrye-reactive-messaging, swagger-ui, vertx, vertx-web]
    2021-06-08 16:48:52,944 INFO  [io.qua.dep.dev.RuntimeUpdatesProcessor] (vert.x-worker-thread-0) Hot replace total time: 1.908s 
    2021-06-08 16:48:53,057 WARN  [org.kie.kog.add.clo.qua.htt.AbstractQuarkusCloudEventResource] (executor-thread-199) Content-Type of the received CloudEvent 'noteapplication' is not supported. Content-type is null. Assuming application/json.
    bilan received Bilan [dl=50.0, ee=2.0, fl=0.0, fm=0.0, ga=2.0, gg=5.0, hn=0.0, hp=1.0, hq=2.0, siren=423646512]
    Variable [type=rentab_13, value=10.0]
    Variable [type=strfin_36, value=25.0]
    notation Notation [decoupageSectoriel=1.0, note=A, orientation=Approved, score=0.0, typeAiguillage=MODELE_1]
  ```

  Then the loan service is triggered but not the process

  ```log
        --/ __ \/ / / / _ | / _ \/ //_/ / / / __/ 
    -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \   
    --\___\_\____/_/ |_/_/|_/_/|_|\____/___/   
    2021-06-08 18:40:58,669 WARN  [io.sma.rea.mes.provider] (Quarkus Main Thread) SRMSG00207: Some components are not connected to either downstream consumers or upstream producers:
      - SubscriberMethod{method:'org.kie.kogito.addon.cloudevents.quarkus.QuarkusCloudEventPublisher#onEvent', incoming:'kogito_incoming_stream'} has no upstream

    2021-06-08 18:40:58,676 INFO  [org.kie.kog.ser.eve.imp.AbstractMessageConsumer] (Quarkus Main Thread) Consumer for class org.redhat.bbank.model.Loan started.
    2021-06-08 18:40:58,677 INFO  [org.kie.kog.add.clo.qua.QuarkusKogitoExtensionInitializer] (Quarkus Main Thread) Registered Kogito CloudEvent extension
    2021-06-08 18:40:58,689 INFO  [io.quarkus] (Quarkus Main Thread) loan 2.0.0-SNAPSHOT on JVM (powered by Quarkus 1.13.3.Final) started in 1.257s. Listening on: http://localhost:8780
    2021-06-08 18:40:58,689 INFO  [io.quarkus] (Quarkus Main Thread) Profile dev activated. Live Coding activated.
    2021-06-08 18:40:58,689 INFO  [io.quarkus] (Quarkus Main Thread) Installed features: [cdi, hibernate-validator, kogito-decisions, kogito-predictions, kogito-processes, kogito-rules, mutiny, qute, rest-client, resteasy, resteasy-jackson, servlet, smallrye-context-propagation, smallrye-health, smallrye-openapi, smallrye-reactive-messaging, swagger-ui, vertx, vertx-web]
    2021-06-08 18:40:58,689 INFO  [io.qua.dep.dev.RuntimeUpdatesProcessor] (vert.x-worker-thread-0) Hot replace total time: 1.300s 
    2021-06-08 18:40:58,778 DEBUG [org.kie.kog.add.clo.qua.htt.AbstractQuarkusCloudEventResource] (executor-thread-199) CloudEvent to publish: 
    ☁ ️cloudevents.Event
    Context Attributes,
      specversion: 1.0
      type: offerapplication
      source: /process/notation
      id: ffeba7c4-5b95-43fa-8df9-e2c477d9aa03
      datatype: null
    Extensions,	kogitousertaskist: 1
      kogitoprocrefid: 2320b592-2dbc-470d-b22c-46e142d16856
      kogitoprocinstanceid: 613ba824-3109-4ff0-8c6d-88b49b73b797
      kogitoprocid: notation
    Data,
      [B@398a2c17
    2021-06-08 18:40:58,778 WARN  [org.kie.kog.add.clo.qua.htt.AbstractQuarkusCloudEventResource] (executor-thread-199) Content-Type of the received CloudEvent 'offerapplication' is not supported. Content-type is null. Assuming application/json.
    2021-06-08 18:40:58,780 DEBUG [org.kie.kog.add.clo.qua.htt.AbstractQuarkusCloudEventResource] (executor-thread-199) Decoded event {"specversion":"1.0","id":"ffeba7c4-5b95-43fa-8df9-e2c477d9aa03","source":"/process/notation","type":"offerapplication","datacontenttype":"application/json","time":"2021-06-08T18:40:57.308968+02:00","kogitousertaskist":"1","kogitoprocrefid":"2320b592-2dbc-470d-b22c-46e142d16856","kogitoprocinstanceid":"613ba824-3109-4ff0-8c6d-88b49b73b797","kogitoprocid":"notation","data":{"siren":"423646512","ca":200000.0,"nbEmployees":10.0,"age":3,"publicSupport":true,"typeProjet":"IRD","amount":50000.0,"notation":{"score":0.0,"note":"A","orientation":"Approved","decoupageSectoriel":1.0,"typeAiguillage":"MODELE_1"},"eligible":true,"msg":"Eligible","bilan":{"siren":"423646512","gg":5.0,"ga":2.0,"hp":1.0,"hq":2.0,"hn":0.0,"fl":0.0,"fm":0.0,"dl":50.0,"ee":2.0,"variables":[{"type":"rentab_13","value":10.0},{"type":"strfin_36","value":25.0}]},"rate":0.0,"nbmonths":0}}
    2021-06-08 18:40:58,781 DEBUG [org.kie.kog.add.clo.qua.QuarkusCloudEventPublisher] (executor-thread-199) Producing message to internal bus: {"specversion":"1.0","id":"ffeba7c4-5b95-43fa-8df9-e2c477d9aa03","source":"/process/notation","type":"offerapplication","datacontenttype":"application/json","time":"2021-06-08T18:40:57.308968+02:00","kogitousertaskist":"1","kogitoprocrefid":"2320b592-2dbc-470d-b22c-46e142d16856","kogitoprocinstanceid":"613ba824-3109-4ff0-8c6d-88b49b73b797","kogitoprocid":"notation","data":{"siren":"423646512","ca":200000.0,"nbEmployees":10.0,"age":3,"publicSupport":true,"typeProjet":"IRD","amount":50000.0,"notation":{"score":0.0,"note":"A","orientation":"Approved","decoupageSectoriel":1.0,"typeAiguillage":"MODELE_1"},"eligible":true,"msg":"Eligible","bilan":{"siren":"423646512","gg":5.0,"ga":2.0,"hp":1.0,"hq":2.0,"hn":0.0,"fl":0.0,"fm":0.0,"dl":50.0,"ee":2.0,"variables":[{"type":"rentab_13","value":10.0},{"type":"strfin_36","value":25.0}]},"rate":0.0,"nbmonths":0}}
    2021-06-08 18:40:58,819 DEBUG [org.kie.kog.ser.eve.imp.AbstractMessageConsumer] (executor-thread-199) Received: org.redhat.bbank.OfferMessageDataEvent_14@95be543 on thread executor-thread-199
    2021-06-08 18:40:58,821 DEBUG [org.kie.kog.eve.imp.CloudEventConsumer] (executor-thread-199) Received message with reference id '2320b592-2dbc-470d-b22c-46e142d16856' going to use it to send signal 'offerapplication'
    2021-06-08 18:40:58,821 WARN  [org.kie.kog.eve.imp.CloudEventConsumer] (executor-thread-199) Process instance with id '2320b592-2dbc-470d-b22c-46e142d16856' not found for triggering signal 'offerapplication'
    2021-06-08 18:40:58,834 INFO  [io.ver.ext.web.han.imp.LoggerHandlerImpl] (executor-thread-199) 127.0.0.1 - - [Tue, 8 Jun 2021 16:40:58 GMT] "POST / HTTP/1.1" 200 0 "-" "Vert.x-WebClient/3.9.6"
  ```

  When calling the service directly it works

    ```json
      curl -X POST \
      -H "content-type: application/json"  \
      -H "ce-specversion: 1.0"  \
      -H "ce-source: /from/localhost"  \
      -H "ce-type: offerapplication" \
      -H "ce-id: 12346"  \
      -d "{\"siren\":\"423646512\",\"ca\":200000.0,\"nbEmployees\":10.0,\"age\":3,\"publicSupport\":true,\"typeProjet\":\"IRD\",\"amount\":50000.0,\"notation\":{\"score\":0.0,\"note\":\"A\",\"orientation\":\"Approved\",\"decoupageSectoriel\":1.0,\"typeAiguillage\":\"MODELE_1\"},\"eligible\":true,\"msg\":\"Eligible\",\"bilan\":{\"siren\":\"423646512\",\"gg\":5.0,\"ga\":2.0,\"hp\":1.0,\"hq\":2.0,\"hn\":0.0,\"fl\":0.0,\"fm\":0.0,\"dl\":50.0,\"ee\":2.0,\"variables\":[{\"type\":\"rentab_13\",\"value\":10.0},{\"type\":\"strfin_36\",\"value\":25.0}]},\"rate\":0.0,\"nbmonths\":0}}" http://localhost:8780
  ```
  ```log
      __  ____  __  _____   ___  __ ____  ______ 
    --/ __ \/ / / / _ | / _ \/ //_/ / / / __/ 
    -/ /_/ / /_/ / __ |/ , _/ ,< / /_/ /\ \   
    --\___\_\____/_/ |_/_/|_/_/|_|\____/___/   
    2021-06-08 18:40:58,669 WARN  [io.sma.rea.mes.provider] (Quarkus Main Thread) SRMSG00207: Some components are not connected to either downstream consumers or upstream producers:
      - SubscriberMethod{method:'org.kie.kogito.addon.cloudevents.quarkus.QuarkusCloudEventPublisher#onEvent', incoming:'kogito_incoming_stream'} has no upstream

    2021-06-08 18:40:58,676 INFO  [org.kie.kog.ser.eve.imp.AbstractMessageConsumer] (Quarkus Main Thread) Consumer for class org.redhat.bbank.model.Loan started.
    2021-06-08 18:40:58,677 INFO  [org.kie.kog.add.clo.qua.QuarkusKogitoExtensionInitializer] (Quarkus Main Thread) Registered Kogito CloudEvent extension
    2021-06-08 18:40:58,689 INFO  [io.quarkus] (Quarkus Main Thread) loan 2.0.0-SNAPSHOT on JVM (powered by Quarkus 1.13.3.Final) started in 1.257s. Listening on: http://localhost:8780
    2021-06-08 18:40:58,689 INFO  [io.quarkus] (Quarkus Main Thread) Profile dev activated. Live Coding activated.
    2021-06-08 18:40:58,689 INFO  [io.quarkus] (Quarkus Main Thread) Installed features: [cdi, hibernate-validator, kogito-decisions, kogito-predictions, kogito-processes, kogito-rules, mutiny, qute, rest-client, resteasy, resteasy-jackson, servlet, smallrye-context-propagation, smallrye-health, smallrye-openapi, smallrye-reactive-messaging, swagger-ui, vertx, vertx-web]
    2021-06-08 18:40:58,689 INFO  [io.qua.dep.dev.RuntimeUpdatesProcessor] (vert.x-worker-thread-0) Hot replace total time: 1.300s 
    2021-06-08 18:40:58,778 DEBUG [org.kie.kog.add.clo.qua.htt.AbstractQuarkusCloudEventResource] (executor-thread-199) CloudEvent to publish: 
    ☁ ️cloudevents.Event
    Context Attributes,
      specversion: 1.0
      type: offerapplication
      source: /process/notation
      id: ffeba7c4-5b95-43fa-8df9-e2c477d9aa03
      datatype: null
    Extensions,	kogitousertaskist: 1
      kogitoprocrefid: 2320b592-2dbc-470d-b22c-46e142d16856
      kogitoprocinstanceid: 613ba824-3109-4ff0-8c6d-88b49b73b797
      kogitoprocid: notation
    Data,
      [B@398a2c17
    2021-06-08 18:40:58,778 WARN  [org.kie.kog.add.clo.qua.htt.AbstractQuarkusCloudEventResource] (executor-thread-199) Content-Type of the received CloudEvent 'offerapplication' is not supported. Content-type is null. Assuming application/json.
    2021-06-08 18:40:58,780 DEBUG [org.kie.kog.add.clo.qua.htt.AbstractQuarkusCloudEventResource] (executor-thread-199) Decoded event {"specversion":"1.0","id":"ffeba7c4-5b95-43fa-8df9-e2c477d9aa03","source":"/process/notation","type":"offerapplication","datacontenttype":"application/json","time":"2021-06-08T18:40:57.308968+02:00","kogitousertaskist":"1","kogitoprocrefid":"2320b592-2dbc-470d-b22c-46e142d16856","kogitoprocinstanceid":"613ba824-3109-4ff0-8c6d-88b49b73b797","kogitoprocid":"notation","data":{"siren":"423646512","ca":200000.0,"nbEmployees":10.0,"age":3,"publicSupport":true,"typeProjet":"IRD","amount":50000.0,"notation":{"score":0.0,"note":"A","orientation":"Approved","decoupageSectoriel":1.0,"typeAiguillage":"MODELE_1"},"eligible":true,"msg":"Eligible","bilan":{"siren":"423646512","gg":5.0,"ga":2.0,"hp":1.0,"hq":2.0,"hn":0.0,"fl":0.0,"fm":0.0,"dl":50.0,"ee":2.0,"variables":[{"type":"rentab_13","value":10.0},{"type":"strfin_36","value":25.0}]},"rate":0.0,"nbmonths":0}}
    2021-06-08 18:40:58,781 DEBUG [org.kie.kog.add.clo.qua.QuarkusCloudEventPublisher] (executor-thread-199) Producing message to internal bus: {"specversion":"1.0","id":"ffeba7c4-5b95-43fa-8df9-e2c477d9aa03","source":"/process/notation","type":"offerapplication","datacontenttype":"application/json","time":"2021-06-08T18:40:57.308968+02:00","kogitousertaskist":"1","kogitoprocrefid":"2320b592-2dbc-470d-b22c-46e142d16856","kogitoprocinstanceid":"613ba824-3109-4ff0-8c6d-88b49b73b797","kogitoprocid":"notation","data":{"siren":"423646512","ca":200000.0,"nbEmployees":10.0,"age":3,"publicSupport":true,"typeProjet":"IRD","amount":50000.0,"notation":{"score":0.0,"note":"A","orientation":"Approved","decoupageSectoriel":1.0,"typeAiguillage":"MODELE_1"},"eligible":true,"msg":"Eligible","bilan":{"siren":"423646512","gg":5.0,"ga":2.0,"hp":1.0,"hq":2.0,"hn":0.0,"fl":0.0,"fm":0.0,"dl":50.0,"ee":2.0,"variables":[{"type":"rentab_13","value":10.0},{"type":"strfin_36","value":25.0}]},"rate":0.0,"nbmonths":0}}
    2021-06-08 18:40:58,819 DEBUG [org.kie.kog.ser.eve.imp.AbstractMessageConsumer] (executor-thread-199) Received: org.redhat.bbank.OfferMessageDataEvent_14@95be543 on thread executor-thread-199
    2021-06-08 18:40:58,821 DEBUG [org.kie.kog.eve.imp.CloudEventConsumer] (executor-thread-199) Received message with reference id '2320b592-2dbc-470d-b22c-46e142d16856' going to use it to send signal 'offerapplication'
    2021-06-08 18:40:58,821 WARN  [org.kie.kog.eve.imp.CloudEventConsumer] (executor-thread-199) Process instance with id '2320b592-2dbc-470d-b22c-46e142d16856' not found for triggering signal 'offerapplication'
    2021-06-08 18:40:58,834 INFO  [io.ver.ext.web.han.imp.LoggerHandlerImpl] (executor-thread-199) 127.0.0.1 - - [Tue, 8 Jun 2021 16:40:58 GMT] "POST / HTTP/1.1" 200 0 "-" "Vert.x-WebClient/3.9.6"
    2021-06-08 18:51:29,459 DEBUG [org.kie.kog.add.clo.qua.htt.AbstractQuarkusCloudEventResource] (executor-thread-199) CloudEvent to publish: 
    ☁ ️cloudevents.Event
    Context Attributes,
      specversion: 1.0
      type: offerapplication
      source: /from/localhost
      id: 12346
      datatype: application/json
    Extensions,
    Data,
      [B@118bd7b
    2021-06-08 18:51:29,459 DEBUG [org.kie.kog.add.clo.qua.QuarkusCloudEventPublisher] (executor-thread-199) Producing message to internal bus: {"specversion":"1.0","id":"12346","source":"/from/localhost","type":"offerapplication","datacontenttype":"application/json","data":{"siren":"423646512","ca":200000.0,"nbEmployees":10.0,"age":3,"publicSupport":true,"typeProjet":"IRD","amount":50000.0,"notation":{"score":0.0,"note":"A","orientation":"Approved","decoupageSectoriel":1.0,"typeAiguillage":"MODELE_1"},"eligible":true,"msg":"Eligible","bilan":{"siren":"423646512","gg":5.0,"ga":2.0,"hp":1.0,"hq":2.0,"hn":0.0,"fl":0.0,"fm":0.0,"dl":50.0,"ee":2.0,"variables":[{"type":"rentab_13","value":10.0},{"type":"strfin_36","value":25.0}]},"rate":0.0,"nbmonths":0}}}
    2021-06-08 18:51:29,459 DEBUG [org.kie.kog.ser.eve.imp.AbstractMessageConsumer] (executor-thread-199) Received: org.redhat.bbank.OfferMessageDataEvent_14@3d254858 on thread executor-thread-199
    2021-06-08 18:51:29,460 DEBUG [org.kie.kog.eve.imp.CloudEventConsumer] (executor-thread-199) Received message without reference id, starting new process instance with trigger 'offerapplication'
    Loan before offer Loan [age=3, amount=50000.0, bilan=Bilan [dl=50.0, ee=2.0, fl=0.0, fm=0.0, ga=2.0, gg=5.0, hn=0.0, hp=1.0, hq=2.0, siren=423646512], ca=200000.0, elligible=true, msg=Eligible, nbEmployees=10.0, notation=Notation [decoupageSectoriel=1.0, note=A, orientation=Approved, score=0.0, typeAiguillage=MODELE_1], publicSupport=true, siren=423646512, typeProjet=IRD, rate=0.0, nbmonths=0]
    notation Notation [decoupageSectoriel=1.0, note=A, orientation=Approved, score=0.0, typeAiguillage=MODELE_1]
    Offer calculated : 2.0 36
    2021-06-08 18:51:29,585 DEBUG [org.kie.kog.ser.eve.imp.DefaultEventMarshaller] (executor-thread-199) Marshalling event org.redhat.bbank.OfferMessageDataEvent_1@1e04f2
    2021-06-08 18:51:29,623 INFO  [io.ver.ext.web.han.imp.LoggerHandlerImpl] (executor-thread-199) 127.0.0.1 - - [Tue, 8 Jun 2021 16:51:29 GMT] "POST / HTTP/1.1" 200 0 "-" "curl/7.64.1"
```
## Deploy on openshift
- Please install : 
  - oc cli : https://docs.openshift.com/container-platform/4.7/cli_reference/openshift_cli/getting-started-cli.html
  - kn cli : https://docs.openshift.com/container-platform/4.7/serverless/installing_serverless/installing-kn.html#installing-kn
  - kogito cli : https://docs.jboss.org/kogito/release/latest/html_single/#proc-kogito-operator-and-cli-installing_kogito-deploying-on-openshift


* connect to Openshift server

  ```sh
  oc login https://ocp-url:6443 -u login -p password
  ```



* Create a new namespace
  ```sh
    export PROJECT=kogito-knative-loan
    oc new-project $PROJECT
  ```
* Install Openshift Serverless and knative-serving 

  * install openshift-serverless operator from OperatorHub

    https://docs.openshift.com/container-platform/4.6/serverless/installing_serverless/installing-openshift-serverless.html

  * create a knative-serving and knative-eventing instance
    ```sh
    ./manifest/scripts/knative-serving.sh
    ./manifest/scripts/knative-eventing.sh
    oc label namespace kogito-knative-bbank bindings.knative.dev/include=true 
    ```

  * create the knative bkoker
    ```sh
    oc apply -f manifest/services/keventing/infra/broker.yml
    ```
  * add the event-display service to follow the cloud native events 
    ```sh
    oc apply -f manifest/services/keventing/kogito-services/event-display-service.yml
    ```
  * deploy a trigger to run event-display service to log all events
   
    ```sh
    oc apply -f ../manifest/services/keventing/trigger/display-all-events-trigger
    ```
    

* Deploy kogito infra and notation service

  * install Kogito operator

  * deploy the kogito infra 
    ```sh
    cd ..
    #kogito install infra kogito-infinispan-infra --kind Infinispan --apiVersion infinispan.org/v1 --resource-name kogito-infinispan 
    oc apply -f ./manifest/services/keventing/infra/kogito-knative-infra.yml -n $PROJECT
    ```

  * deploy eligibility/notation/loan  service

    ```sh
    #create the service throw kogito operator 
    oc apply -f ./manifest/services/keventing/kogito-services/eligibility-kogitoapp.yml
    cd eligibility
    mvn clean package  -DskipTests=true
    oc start-build eligibility --from-dir=target 
    ```
    
    ```sh
    #create the service throw kogito operator 
    cd ../notation
    oc apply -f ../manifest/services/keventing/kogito-services/notation-kogitoapp.yml
    mvn clean package  -DskipTests=true
    oc start-build notation --from-dir=target 
    ```
    * get the eligibility route
    ```sh
    projects/kogito-knative-loan main ❯ oc get route
        NAME          HOST/PORT                                                         PATH   SERVICES      PORT   TERMINATION   WILDCARD
        eligibility   eligibility-loan.apps.cluster-152a.152a.sandbox1775.opentlc.com          eligibility   http                 None
        loan          loan-loan.apps.cluster-152a.152a.sandbox1775.opentlc.com                 loan          http                 None
        notation      notation-loan.apps.cluster-152a.152a.sandbox1775.opentlc.com 
    ```
    * We are ready for tests, 😆

        * call the eligibility service
            ```sh
                curl -X POST \
                    -H "content-type: application/json"  \
                    -H "ce-specversion: 1.0"  \
                    -H "ce-source: /from/localhost"  \
                    -H "ce-type: eligibilityapplication" \
                    -H "ce-id: 12346"  \
                    -d "{\"age\":3,\"amount\":50000,\"bilan\":{\"gg\":5,\"ga\":2,\"hp\":1,\"hq\":2,\"dl\":50,\"ee\":2,\"siren\":\"423646512\",\"variables\":[]},\"ca\":200000,\"eligible\":false,\"msg\":\"string\",\"nbEmployees\":10,\"notation\":{\"decoupageSectoriel\":0,\"note\":\"string\",\"orientation\":\"string\",\"score\":0,\"typeAiguillage\":\"string\"},\"publicSupport\":true,\"siren\":\"423646512\",\"typeProjet\":\"IRD\"}" \
                    eligibility-loan.apps.cluster-152a.152a.sandbox1775.opentlc.com 
            ```
            The call should invoke the eligibility service that produce another event with type `noteapplication` that invoke the notation service, in fact only the eligibility service.
            both trigger for eligibility and notation services generated by the operator are using the same filter type  `kogito-incoming-stream`
            
            ```sh
                oc get trigger
                    NAME                            BROKER    SUBSCRIBER_URI                                     AGE     READY   REASON
                    display-all-events-ed-trigger   default   http://cloudevent-display.loan.svc.cluster.local   46h     True    
                    eligibility-listener-1203       default   http://eligibility.loan.svc.cluster.local/         23h     True    
                    loan-listener-8016              default   http://loan.loan.svc.cluster.local/                47h     True    
                    notation-listener-1762          default   http://notation.loan.svc.cluster.local/            5h11m   True    

                oc get trigger notation-listener-1762  -o yaml  
                    apiVersion: eventing.knative.dev/v1
                    kind: Trigger
                    metadata:
                    annotations:
                        eventing.knative.dev/creator: system:serviceaccount:loan:kogito-operator-controller-manager
                        eventing.knative.dev/lastModifier: opentlc-mgr
                    creationTimestamp: 
                    generation: 2
                    labels:
                        app: notation
                        eventing.knative.dev/broker: default
                        kogito.kie.org/messageEventId: kogito_incoming_stream
                    managedFields:
                    selfLink: /apis/eventing.knative.dev/v1/namespaces/loan/triggers/notation-listener-1762
                    uid: c5446e63-1d5d-4546-b09d-4e707831afe0
                    spec:
                    broker: default
                    filter:
                        attributes:
                        type: kogito-incoming-stream
                    subscriber:
                        ref:
                        apiVersion: v1
                        kind: Service
                        name: notation
                        namespace: loan
                    status:
                    conditions:
                    observedGeneration: 2
                    subscriberUri: http://notation.loan.svc.cluster.local/ 
                ```

            the operator seems not using the eventsMeta object

            ```sh
                curl -X 'GET' \
                'http://notation-loan.apps.cluster-152a.152a.sandbox1775.opentlc.com/messaging/topics'
            ```

            ```json
                [
                    {
                        "name": "kogito_outgoing_stream",
                        "type": "OUTGOING",
                        "eventsMeta": [
                        {
                            "type": "process.notation.offerapplication",
                            "source": "/process/notation",
                            "kind": "PRODUCED"
                        }
                        ]
                    },
                    {
                        "name": "kogito_incoming_stream",
                        "type": "INCOMING",
                        "eventsMeta": [
                        {
                            "type": "noteapplication",
                            "source": "",
                            "kind": "CONSUMED"
                        }
                        ]
                    }
                ]
            ```

