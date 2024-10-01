# Solvo Testing Assignment

Solvo Testing Assignment - это REST API приложение на основе Spring Boot.
Используется cборка Spring приложения через авто-конфигурацию. Для скрытия очевидных операций и
для улучшения читабельности кода использован lombok.
Для работы с данными используется:
- СУБД Postgres
- Spring Boot JPA
- Миграции БД с flywaydb
- Документация с Swagger
- Webflux
- Lombok

## Документация
Основныe endpoints:

| Метод  | Путь                                | Описание                                            |
|--------|-------------------------------------|-----------------------------------------------------|
| GET    | /transactions                       | Получение списка транзакций                         |
| GET    | /transactions/limit-exceeded        | Получение списка транзакций превысивщихся лимимт    |
| POST   | /transactions/add-transaction       | Создание новый транзакций                           |
| GET    | /limit                              | Получение списка всех лимитов                       |
| POST   | /limit/new-limit                    | Установка нового лимита                             |


Документация находится в Swagger после запуска приложения:
http://localhost:8080/swagger-ui/index.html

## Требования

- Java 17
- Gradle 8.3

## Запуск проекта

1. **Клонировать репозиторий:**
    ```bash
    git clone https://github.com/ShortMikelon/Solvo.git
    ```
    
2. **Перейти в каталог проекта:**
    ```bash
    cd Solvo
    ```
   
3. **Запуск приложения:**
    ```bash
    mvn spring-boot:run
    ```

