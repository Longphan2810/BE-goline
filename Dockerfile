#FROM openjdk:17-jdk
#
#WORKDIR /usr/app
#
#COPY /target/CodeBase-DATN-0.0.1-SNAPSHOT.jar /usr/app/DATN.jar
#
#EXPOSE 8080
#
#ENTRYPOINT ["java",  "-jar", "DATN.jar"]
#
##docker run -e SPRING_DATASOURCE_URL=jdbc:mysql://junction.proxy.rlwy.net:58967/railway?useSSL=false -e SPRING_DATASOURCE_USERNAME=root -e SPRING_DATASOURCE_PASSWORD=qwFRxQddCqWbwxMnbEUdILeVBiLqOXKk -p 8080:8080 test-aftere-connectdb2


# Sử dụng hình ảnh maven làm base image
FROM maven:3.8-openjdk-17-slim AS build

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép toàn bộ mã nguồn vào container
COPY . .

# Chạy lệnh mvn clean install để build dự án
RUN mvn clean install -DskipTests

# Sử dụng hình ảnh java cho ứng dụng production
FROM openjdk:17-jdk-slim

# Đặt thư mục làm việc trong container
WORKDIR /usr/app

# Sao chép file JAR từ container build sang container production
COPY --from=build /app/target/CodeBase-DATN-0.0.1-SNAPSHOT.jar /usr/app/CodeBase-DATN-0.0.1-SNAPSHOT.jar

# Mở cổng mà ứng dụng của bạn sẽ chạy
EXPOSE 8080

# Chạy ứng dụng Java
CMD ["java", "-jar", "CodeBase-DATN-0.0.1-SNAPSHOT.jar"]