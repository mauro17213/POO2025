pipeline {
    agent { label 'Linux' }

    tools {
        maven 'Maven'
        jdk 'jdk11'
    }

    environment {
        WILDFLY_HOME = "${WORKSPACE}/wildfly-19.1.0.Final"
        DEPLOYMENTS  = "${WILDFLY_HOME}/standalone/deployments"
        EAR_NAME     = 'savia-ear.ear'
    }

    stages {
        stage('Checkout SCM') {
            steps {
                checkout scm
            }
        }

        stage('Build Modules') {
            steps {
                script {
                    echo '? Compilando módulos...'
                    sh '''
                        set -e
                        
                        # Build modules in the correct dependency order
                        echo '? Compilando savia-negocio...'
                        mvn -f "${WORKSPACE}/savia-negocio/pom.xml" clean install -DskipTests -B -U

                        echo '? Compilando savia-ejb...'
                        mvn -f "${WORKSPACE}/savia-ejb/pom.xml" clean install -DskipTests -B -U

                        echo '? Compilando savia-web...'
                        mvn -f "${WORKSPACE}/savia-web/pom.xml" clean install -DskipTests -B -U
                        
                        echo '? Empaquetando savia-ear...'
                        mvn -f "${WORKSPACE}/savia-ear/pom.xml" clean package -DskipTests -B -U

                        # Rename EAR to a stable name
                        generatedEar=$(ls savia-ear/target/*.ear | head -n 1)
                        [ -f "$generatedEar" ] || { echo "? No se generó el EAR"; exit 1; }
                        cp "$generatedEar" "savia-ear/target/${EAR_NAME}"
                        echo "? EAR renombrado a ${EAR_NAME}"
                    '''
                    echo '? Compilación completa'
                }
            }
        }

        stage('Stop WildFly') {
            steps {
                script {
                    echo '? Deteniendo WildFly...'
                    sh "sudo ${WILDFLY_HOME}/bin/jboss-cli.sh --connect command=:shutdown || true"
                    sleep 5
                    echo '? WildFly detenido (si estaba en ejecución).'
                }
            }
        }

        stage('Deploy EAR') {
            steps {
                script {
                    echo '? Desplegando el EAR...'
                    sh '''
                        set -e
                        mkdir -p "${DEPLOYMENTS}"
                        rm -rf "${DEPLOYMENTS}/${EAR_NAME}" "${DEPLOYMENTS}/${EAR_NAME}.dodeploy" || true
                        cp "${WORKSPACE}/savia-ear/target/${EAR_NAME}" "${DEPLOYMENTS}/"
                        touch "${DEPLOYMENTS}/${EAR_NAME}.dodeploy"
                        echo "? EAR copiado a la carpeta de despliegues."
                    '''
                }
            }
        }

        stage('Start WildFly') {
            steps {
                script {
                    echo '?? Iniciando WildFly...'
                    sh "nohup ${WILDFLY_HOME}/bin/standalone.sh -b 0.0.0.0 > ${WILDFLY_HOME}/standalone/log/boot.out 2>&1 &"
                    echo '? WildFly iniciado.'
                }
            }
        }

        stage('Verify Deployment') {
            steps {
                script {
                    def logPath = "${WILDFLY_HOME}/standalone/log/server.log"
                    echo "? Verificando el despliegue en el log de WildFly..."
                    timeout(time: 180, unit: 'SECONDS') {
                        waitUntil {
                            sh(script: "test -f ${logPath}", returnStatus: true) == 0 &&
                            sh(script: "tail -n 200 ${logPath} | grep 'Deployed \"${EAR_NAME}\"'", returnStatus: true) == 0
                        }
                    }
                    echo '? Despliegue verificado y exitoso.'
                }
            }
        }
    }
}