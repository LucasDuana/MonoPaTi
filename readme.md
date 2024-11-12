# MonoPati

## User Service
### Descripcion
Este servicio se encarga de la gestion de usuarios y cuentas,donde cada usuario tiene una o mas cuentas asociadas y cada cuenta tiene mas de una cuenta asociada.

## Endpoints
### Users
#### 1. Obtener Usuarios
- **URL**: `/users`
- **Método**: `GET`

#### 2. Obtener Usuario por ID    
- **URL**: `/users/{id}`
- **Método**: `GET`
- **Path Variables**:
    - `{id}` Long: id de usuario
#### 3. Crear Usuario
- **URL**: `/users`
- **Método**: `POST`
- **Request Body**:
    ```json
    {
     "userName": "jdoe",
     "email": "jdoe@example.com",
     "phoneNumber": "+123456789",
     "firstName": "John",
     "lastName": "Doe"
    }
    ```
#### 4. Actualizar Usuario
- **URL**: `/users/{id}`
- **Método**: `PUT`
- **Path Variables**:
    - `{id}` Long:id de usuario
- **Request Body**:
    ```json
    {
     "userName": "jdoe",
     "email": "jdoe@example.com",
     "phoneNumber": "+123456789",
     "firstName": "John",
     "lastName": "Doe"
    }
    ```
#### 5. Borrar usuario
- **URL**: `/users/{id}`
- **Método**: `DELETE`
- **Path Variables**:
    - `{id}` Long:id de usuario

#### 6. Obtener Scooters cercanos a un usuario



### Accounts
#### 1. Obtener Cuentas
- **URL**: `/accounts`
- **Método**: `GET`

#### 2. Obtener Cuenta por ID
- **URL**: `/accounts/{id}`
- **Método**: `GET`
- **Path Variables**:
    - `{id}` Long:id de cuenta

#### 3. Crear Cuenta
- **URL**: `/accounts`
- **Método**: `POST`
- **Request Body**:
    ```json
    {
     "name": "Cuenta de Ahorro",
     "balance": 1500.75,
     "creationDate": "2024-11-11"
    }
    ```
#### 4. Actualizar Cuenta
- **URL**: `/accounts/{id}`
- **Método**: `PUT`
- **Path Variables**:
    - `{id}` Long:id de cuenta
- **Request Body**:
    ```json
    {
     "name": "Cuenta de Ahorro",
     "balance": 1500.75,
     "creationDate": "2024-11-11"
    }
    ```

#### 5. Borrar Cuenta
- **URL**: `/accounts/{id}`
- **Método**: `DELETE`
- **Path Variables**:
    - `{id}` Long:id de cuenta

#### 6. Anular Cuenta
- **URL**: `/{id}/disabled`
- **Método**: `PATCH`
- **Path Variables**:
    - `{id}` Long:id de cuenta


## Scooter Service
### Descripcion
Este servicio se encarga de gestiona el mantenimiento y el uso de los scooters, tambien se gestionan las paradas donde estos puede quedarse.

### Endpoints
#### Scooters

#### 1. Obtener scooter
- **URL**: `/scooter`
- **Método**: `GET`

#### 2. Obtener Scooter por ID
- **URL**: `/scooters/{id}`
- **Método**: `GET`
- **Path Variables**:
  - `{id}` Long: id de scooter

#### 3. Crear scooter
- **URL**: `/scooter`
- **Método**: `POST`
- **Request Body**:
    ```json
      {
        "name":"ScooterB",
        "status": "Opertativo",
        "latitude": 
        "longitude": ;
      }
  ```


- `POST /scooters` : Crea un nuevo scooter
- `PUT /scooters/{id}` : Actualiza un scooter por su id
- `DELETE /scooters/{id}` : Elimina un scooter por su id
- `GET /scooters/kilometers-report` : Obtiene todos los scooter ordenados de manera decreciente por kms recorridos
- `GET /scooters/filter-by-travels` : Obtiene scooter con una cantidad determinada de viajes en un año especifico
- `GET /scooters/total-timeUsage` : Obtiene los scooter con su tiempo total de uso sin pausas
- `GET /scooters/total-timeUsage-withPauses` : Obtiene los scooter con su tiempo total de uso con pausas
- `GET /scooters/in-use` : Obtiene los scooter en uso
- `GET /scooters/in-maintenance` : Obtiene los scooter en mantenimiento
- `PUT /scooters/{id}/maintenance` : Pone un scooter en mantenimiento mediante su id
- `PATCH /scooters/{id}/location` : Actualiza la localizacion de un scooter mediante su id
- `PATCH /scooters/{idScooter}/stoppings/{idStopping}` : Registra un scooter con su id una parada usando tambien el id de esta

#### Stoppings
- `GET /stoppings` : Obtiene todas las paradas
- `POST /stoppings` : Crea una nueva parada
- `GET /stoppings/{id}` : Obtiene una parada por su id
- `PUT /stoppings/{id}` : Actualiza una parada por su id
- `DELETE /stoppings/{id}` : Elimina una parada por su id

## Travel Service
### Descripcion
Este servicio gestiona y supervisa los trayectos realizados en scooters dentro de un sistema de transporte
-`GET /TRAVELS`: Retorna una lista de todos los viajes
-`POST /Travel`: Crea un nuevo Travel
-`POST /FinalizarTravel`: Finaliza el viaje especificado si el scooter está en el punto de parada final.
-`GET /Travel{id}`: Obtiene viaje por id
-`PUT /Travel{id}`: Actualiza un viaje por su identificador
-`DELETE /Travel/{id}`: Elimina un viaje por su id

## Admin Service
### Descripcion
Este servicio gestiona las operaciones relacionadas con los administradores y el reporte de uso de scooters
- `GET /Admins`: Obtiene una lista de todos los admins
- `GET /Admin{id}`: Obtiene un admin por su identificador
- `POST /Admin`: Crea un admin nuevo
- `PUT /Admin{id}`: Actualiza un admin por su id
- `DELETE /Admin{id}`: Elimina un admin mediante su id
- `GET /total-amount-in-year`: Obtiene en cierto año y una cantidad desde un mes deseado hacia su fin de mes enviado por parametro
- `GET /total-invoice-amount`: Obtiene el total y muestra el monto total de todas las de facturas dentro de un rango
- `GET /scooter-usage-report`: Genera un informe de uso de scooters, incluyendo la duración total de uso efectivo y la distancia total recorrida por cada scooter