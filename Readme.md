## Spring Boot Microservice written in Kotlin  
This project shows how to write a Spring Boot Microservices using Kotlin.  To get more information about this project please go [here](https://medium.com/@erwinalberto/part-1-developing-a-spring-boot-micorservices-using-kotlin-6a20aacbd53e).

## How to run this application

You will need to build the application.
```
$ gradle assemble
```
The command will generate healthcare-openpayment-data-1.0.jar under
```
$ projectlocation/build/libs
```
You will then need to run the jar using the following command
```
$ java -jar build/libs/healthcare-openpayment-data-1.0.jar
```

You can also run the unit tests as well as the Cucumber tests.
```
$ gradle build
```

## Running it in OpenShift

#### Create a docker image
```
$ docker build -t ealberto/openpaymentdata .
```

#### Push docker image to Docker Hub
```
$ docker push ealberto/openmaymentdata
```
note: You will need to have a DockerHub account.


#### Deploying in OpenShift via Minishift

Redhat provides a real easy way to run OpenShift locally using Minishift.  Minishift is a single-node OpenShift cluster running inside a virtual machine.

To install Minishift, follow the [directions](https://docs.openshift.org/latest/minishift/getting-started/installing.html) in this installation guide.  As a note, I'm running these examples in MacOSX but I use VirtualBox as my VM driver.  

```
$ minishift start --vm-driver=virtualbox
```

#### Accessing to the OpenShift CLI (oc)
```
$ minishift oc-env
$ eval $(minishift oc-env)
```

#### Deploying the application to OpenShift
To deploy the application, you will need to set a few environment variables.  Please copy `openshift.env.template` into another `openshift.env` and update the variables accordingly.
Please note that we are using a Docker image (ealberto/simpleshift) hosted in Dockerhub to create the application.  So, before you can run the following command, please make sure you have built your docker image.
```
$ oc new-app ealberto/openpaymentdata
$ oc expose svc/openpaymentdata
```

#### Open the application in the browser
```
$ minishift openshift service openpaymentdata -n open-payment-data
```
