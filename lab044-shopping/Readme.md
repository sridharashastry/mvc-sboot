1. Micrometer dependency added in all the pom.xml.
2. Added property management.tracing.sampling.probability=1.0 in all the properties files.
3. Removed the Circuit Breaker in order Service.
4. Before running this project, ensure zipkin is also running.
5. Below are two level steps to run it on docker

#Docker pull zipkin

	PowerShell 7.6.0-preview.2
	PS C:\ws> docker pull openzipkin/zipkin:latest
	latest: Pulling from openzipkin/zipkin
	c4c4bb013fb3: Pull complete
	b7c17afa2930: Pull complete
	5c328a5614df: Pull complete
	7daf755e9979: Pull complete
	f17ded1a925a: Pull complete
	8cd588dc1145: Pull complete
	003719cf7591: Pull complete
	4fc7e763ce5b: Pull complete
	4a521f81fda9: Pull complete
	Digest: sha256:c2830e93d95de43f0d10e00a3784a9ee101cdf4909370196c8802514fd7f1954
	Status: Downloaded newer image for openzipkin/zipkin:latest
	docker.io/openzipkin/zipkin:latest

	Whats Next?
	  View a summary of image vulnerabilities and recommendations → docker scout quickview openzipkin/zipkin:latest
	PS C:\ws>


#Run the container

PS C:\ws> docker run -d -p 9411:9411 --name zipkin openzipkin/zipkin:latest
66f6fb00d65abc866d2e7db2ade8c7088669f633a3b9d8b76ab1e7633847fa3e
PS C:\ws>




#Open the browser and use below URL to open the zipkin UI

http://localhost:9411/zipkin/


