
PS C:\ws\sboot\lab048> dir

    Directory: C:\ws\sboot\lab048

Mode                 LastWriteTime         Length Name
----                 -------------         ------ ----
d----            2/4/2025  5:23 PM                .mvn
d----            2/4/2025  5:23 PM                src
d----            2/4/2025  5:23 PM                target
-a---            2/4/2025  5:25 PM             26 .dockerignore
-a---            2/4/2025  4:26 PM             38 .gitattributes
-a---            2/4/2025  4:26 PM            395 .gitignore
-a---            2/4/2025  5:29 PM            185 commands-docker.sh
-a---            2/4/2025  5:25 PM            302 Dockerfile
-a---            2/4/2025  4:26 PM           1243 HELP.md
-a---            2/4/2025  4:26 PM          10665 mvnw
-a---            2/4/2025  4:26 PM           6912 mvnw.cmd
-a---            2/4/2025  5:24 PM           1424 pom.xml

PS C:\ws\sboot\lab048>


PS C:\ws\sboot\lab048> mvn wrapper:wrapper
[INFO] Scanning for projects...
Downloading from redhat-ga-repository: http://maven.repository.redhat.com/ga/org/jooq/jooq-codegen-maven/3.19.18/jooq-codegen-maven-3.19.18.pom
Downloading from central: https://repo.maven.apache.org/maven2/org/jooq/jooq-codegen-maven/3.19.18/jooq-codegen-maven-3.19.18.pom
Downloaded from central: https://repo.maven.apache.org/maven2/org/jooq/jooq-codegen-maven/3.19.18/jooq-codegen-maven-3.19.18.pom (3.8 kB at 27 kB/s)

.......
......

[INFO]
[INFO] -----------------< com.bring:hworld-docker-distroless >-----------------
[INFO] Building hworld-docker-distroless 0.0.1-SNAPSHOT
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-wrapper-plugin:3.3.2:wrapper (default-cli) @ hworld-docker-distroless ---
Downloading from redhat-ga-repository: http://maven.repository.redhat.com/ga/org/apache/maven/wrapper/maven-wrapper-distribution/3.3.2/maven-wrapper-distribution-3.3.2-only-script.zip
Downloading from central: https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper-distribution/3.3.2/maven-wrapper-distribution-3.3.2-only-script.zip
Downloaded from central: https://repo.maven.apache.org/maven2/org/apache/maven/wrapper/maven-wrapper-distribution/3.3.2/maven-wrapper-distribution-3.3.2-only-script.zip (7.8 kB at 180 kB/s)
[INFO] Unpacked only-script type wrapper distribution org.apache.maven.wrapper:maven-wrapper-distribution:zip:only-script:3.3.2
[INFO] Configuring .mvn/wrapper/maven-wrapper.properties to use Maven 3.8.4 and download from https://repo.maven.apache.org/maven2
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  19.561 s
[INFO] Finished at: 2025-02-04T17:31:31Z
[INFO] -----------------



			docker build -t hworld:latest .
      docker images
			docker run -p 8080:8080 hworld:latest

			#in another terminal
			docker container list

			http://localhost:8080/api/hello

