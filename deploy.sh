#!/bin/bash
set -e
TOMCAT_HOME=/usr/local/tomcat
WAR_NAME=guvnah-service

export JPDA_ADDRESS=8000
export JPDA_TRANSPORT=dt_socket

echo "shutdown tomcat"
$TOMCAT_HOME/bin/shutdown.sh

echo "mvn clean"
mvn clean

echo "mvn install"
mvn -DskipTests install 
#mvn clean install -P dev 

echo "removing previous build"
rm -rf $TOMCAT_HOME/webapps/$WAR_NAME*

echo "copy new build $WAR_NAME"
cp ./target/$WAR_NAME.war $TOMCAT_HOME/webapps/

echo "startup tomcat"
$TOMCAT_HOME/bin/catalina.sh jpda start

echo "tail the tomcat log"
tail -f $TOMCAT_HOME/logs/catalina.out | awk '
        {
                if($0 ~ /DEBUG/) {
                        print "\033[36m" $0 "\033[39m"
                }
                else if($0 ~ /INFO/) {
                        print "\033[32m" $0 "\033[39m"
                }
                else if($0 ~ /WARN/) {
                        print "\033[34m" $0 "\033[39m"
                }
                else if($0 ~  /SEVERE/) {
                        print "\033[31m" $0 "\033[39m"
                }
                else {
                        print "\033[35m" $0 "\033[39m"
                }
        }
'


