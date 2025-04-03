```mermaid
sequenceDiagram
    title Клиент-Серверная архитектура с взаимодействием с БД
    actor Клиент
    participant Сервер приложений as Сервер
    participant Сервер БД as БД
    
    Клиент->>Сервер: Запрос (HTTP/gRPC)
    activate Сервер
    Сервер->>БД: SQL-запрос
    activate БД
    БД-->>Сервер: Результат
    deactivate БД
    Сервер-->>Клиент: Ответ (JSON/XML)
    deactivate Сервер
```
