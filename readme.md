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
- **URL**: `/scooters`
- **Método**: `GET`

#### 2. Obtener Scooter por ID
- **URL**: `/scooters/{id}`
- **Método**: `GET`
- **Path Variables**:
  - `{id}` Long: id de scooter


#### AGREGA UN MONOPATIN
#### 3. Crear scooter
- **URL**: `/scooter`
- **Método**: `POST`
- **Request Body**:
    ```json
      {
        "name":"ScooterB",
        "status": "Operativo",
        "latitude": 0,
        "longitude": 0
        "stopping":
                    {"stoppingId": 7}
      }
  ```

#### 4. Crear scooter (FUNCIONALIDAD DE ENUNCIADO)
- **URL**: `/scooters{id}`
- **Método**: `PUT`
- **Request Body**:
    ```json
      {
        "name":"ScooterB",
        "status": "Mantanimiento",
        "latitude": 100,
        "longitude": 5
      }
  ```
- **Path Variables**:
  - `{id}` Long: id de scooter
 
#### 5. Borrar usuario
- **URL**: `/scooters/{id}`
- **Método**: `DELETE`
- **Path Variables**:
    - `{id}` Long:id de scooter
 
#### 6. Obtener Todos los scooter ordenados de manera decreciente por kms recorridos (FUNCIONALIDAD DE ENUNCIADO)
- **URL**: `/scooters/kilometers-report`
- **Método**: `GET`
- **Response Body**: 
    ```json
        [
          {
            "name":"ScooterB",
            "status": "Operativo",
            "totalDistance": 10000
            "latitude": 0,
            "longitude": 0
          } ,
           {
            "name":"ScooterH",
            "status": "Operativo",
            "totalDistance": 8000
            "latitude": 0,
            "longitude": 0
          },
           {
            "name":"Scooterc",
            "status": "Operativo",
            "totalDistance": 1000
            "latitude": 0,
            "longitude": 0
          }      
        ]
     ```

#### 7. Obtener una cantidad determinada de scooter en un año determinado (FUNCIONALIDAD DE ENUNCIADO)
- **URL**: `/scooters/filter-by-travels`
- **Método**: `GET`
- **Request Body**: 
   ```json
        
          {
            "Travels": "Cantidad de viajes en integer",
            "year": 2024,
          } 
     ```

#### 8. Obtener Un Listado con el tiempo total de uso de los scooters (FUNCIONALIDAD DE ENUNCIADO)
- **URL**: `/scooters/total-timeUsage`
- **Método**: `GET`
- **Response Body**: 
    ```json
        [
          {
            "scooterName": "ScooterZ",
            "effectiveUsageTime": "2 HORAS Y 54 MINUTOS",
            "scooterId": 5,
          } ,
           {
            "scooterName": "ScooterB",
            "effectiveUsageTime": "7 horas y 0 minutos",
            "scooterId": 9,
          } ,
         {
            "scooterName": "ScooterJ",
            "effectiveUsageTime": "1 HORAS Y 14 MINUTOS",
            "scooterId": 51,
          } ,   
        ]
     ```

#### 9. Obtener Un Listado con el tiempo total de uso de los scooters con pausas (FUNCIONALIDAD DE ENUNCIADO)
- **URL**: `/scooters/total-timeUsage-withPauses`
- **Método**: `GET`
- **Response Body**: 
    ```json
        [
          {
            "scooterName": "ScooterZ"
            "effectiveUsageTime": "1 HORAS Y 54 MINUTOS"
            "scooterId": 5
          } ,
           {
            "cooterName": "ScooterB"
            "effectiveUsageTime": "5 horas y 3 minutos"
            "scooterId": 9
          } ,,
         {
            "scooterName": "ScooterJ"
            "effectiveUsageTime": "0 HORAS Y 33 MINUTOS"
            "scooterId": 51
          } ,   
        ]
    ```

#### 10. Obtener Todos los scooter cuyo status es operativo (FUNCIONALIDAD DE ENUNCIADO)
- **URL**: `/scooters/in-use`
- **Método**: `GET`
- **Response Body**: 
    ```json
        [
          {
            "name":"ScooterB",
            "status": "Operativo",
            "totalDistance": 10000
            "status"
            "latitude": 0,
            "longitude": 0
          } ,
           {
            "name":"ScooterH",
            "status": "Operativo",
            "totalDistance": 8000
            "latitude": 0,
            "longitude": 0
          },
           {
            "name":"Scooterc",
            "status": "Operativo",
            "totalDistance": 1000
            "latitude": 0,
            "longitude": 0
          }      
        ]
     ```

#### 11. Obtener Todos los scooter cuyo status es en mantenimiento (FUNCIONALIDAD DE ENUNCIADO)
- **URL**: `/scooters/in-maintenance`
- **Método**: `GET`
- **Response Body**: 
    ```json
        [
          {
            "name":"ScooterB",
            "status": "Mantenimiento",
            "totalDistance": 10000
            "status"
            "latitude": 0,
            "longitude": 0
          } ,
           {
            "name":"ScooterJ",
            "status": "Mantenimiento",
            "totalDistance": 8000
            "latitude": 0,
            "longitude": 0
          },
           {
            "name":"ScooterP",
            "status": "Mantenimiento",
            "totalDistance": 1000
            "latitude": 0,
            "longitude": 0
          }      
        ]
     ```


#### 12. Finaliza el mantenimiento de un scooter (FUNCIONALIDAD DE ENUNCIADO)
- **URL**: `/scooters/{id}/maintenance`
- **Método**: `PATCH`
- **Path Variables**:
    - `{id}` Long:id de scooter
 
#### 13. Actualiza la localizacion de un scoooter mediante su id (FUNCIONALIDAD DE ENUNCIADO)
- **URL**: `/scooters/{id}/location`
- **Método**: `PATCH`
- **Path Variables**:
    - `{id}` Long:id de scooter

#### 14. Registra un scooter mediante su id en una parada usando el id de esta tambien (FUNCIONALIDAD DE ENUNCIADO)
- **URL**: `/scooters/{idScooter}/stoppings/{idStopping}`
- **Método**: `PATCH`
- **Path Variables**:
    - `{idScooter}` Long:id de scooter
    - `{idStopping}` Long:id de stopping             
    



#### Stoppings


#### 1. Obtener Paradas
- **URL**: `/stoppings`
- **Método**: `GET`

#### 2. Obtener Parada por ID    
- **URL**: `/stoppings/{id}`
- **Método**: `GET`
- **Path Variables**:
    - `{id}` Long: id de parada
#### 3. Crear Parada
- **URL**: `/stoppings`
- **Método**: `POST`
- **Request Body**:
    ```json
    {
     "name": "Parada006",
     "address": "Dinamarca 2045",
     "latitude": 0,
     "longitude": 0,
    }
    ```


#### 4. Actualizar Parada
- **URL**: `/stoppings/{id}`
- **Método**: `PUT`
- **Path Variables**:
    - `{id}` Long:id de parada
- **Request Body**:
    ```json
    {
      "name": "Parada023",
      "address": "Cochabamba 569",
      "latitude": 100,
      "longitude": 100,
    }
    ```
#### 5. Borrar Parada
- **URL**: `/stoppings/{id}`
- **Método**: `DELETE`
- **Path Variables**:
    - `{id}` Long:id de parada


## Travel Service

### Descripcion
Este servicio gestiona y supervisa los trayectos realizados en scooters dentro de un sistema de transporte


### Endpoints

#### Travels

#### 1. Obtener viajes
- **URL**: `/travels`
- **Método**: `GET`

  

#### 2. Obtener Viaje por ID    
- **URL**: `/travels/{id}`
- **Método**: `GET`
- **Path Variables**:
    - `{id}` Long: id de viaje

  
#### 3. Crear Viaje
- **URL**: `/travels`
- **Método**: `POST`
- **Request Body**:
    ```json
    {
      "userId": 7,
      "scooterId" 34,
      "date": "20-05-2024",
      "startTime": "2024-05-2024T13:45:20.123",
      "endTime": "2024-05-2024T15:45:20.123",
    }
    ```

#### 4. Actualizar Viaje
- **URL**: `/travels/{id}`
- **Método**: `PUT`
- **Path Variables**:
    - `{id}` Long:id de viaje
- **Request Body**:
    ```json
    {
      "date": "26-05-2024",
      "startTime": "2024-05-2024T13:45:20.123",
      "endTime": "2024-05-2024T15:45:20.123",
    }
    ```
    
#### 5. Borrar Viaje
- **URL**: `/travels/{id}`
- **Método**: `DELETE`
- **Path Variables**:
    - `{id}` Long:id de viaje

#### 6. Finalizar Viaje
- **URL**: `travels/{id}/finalizar`
- **Método**: `GET`
- **Path Variables**:
    - `{id}` Long:id de viaje


 
#### Pauses


#### 1. Obtener Pausas
- **URL**: `/pauses`
- **Método**: `GET`

  

#### 2. Obtener Pausa por ID    
- **URL**: `/pauses/{id}`
- **Método**: `GET`
- **Path Variables**:
    - `{id}` Long: id de pausa

  
#### 3. Crear Pausa
- **URL**: `/pauses`
- **Método**: `POST`
- **Request Body**:
    ```json
    {
      "startTime": "2024-05-2024T13:45:20.123",
      "endTime": "2024-05-2024T14:00:20.123",
      "travel": {"travelId": 3}
    }
    ```

#### 4. Actualizar Pausas
- **URL**: `/pauses/{id}`
- **Método**: `PUT`
- **Path Variables**:
    - `{id}` Long:id de pausa
- **Request Body**:
    ```json
    {
      "startTime": "2024-05-2024T13:45:20.123",
      "endTime": "2024-05-2024T15:45:20.123",
    }
    ```
    
#### 5. Borrar Pausa
- **URL**: `/pauses/{id}`
- **Método**: `DELETE`
- **Path Variables**:
    - `{id}` Long:id de pausa
      

## Admin Service
### Descripcion
Este servicio gestiona las operaciones relacionadas con los administradores y el reporte de uso de scooters

### Endpoints

#### admin


#### 1. Obtener Admin
- **URL**: `/admins`
- **Método**: `GET`

  

#### 2. Obtener Admin por ID    
- **URL**: `/admins/{id}`
- **Método**: `GET`
- **Path Variables**:
    - `{id}` Long: id de admin

  
#### 3. Crear Admin
- **URL**: `/admins`
- **Método**: `POST`
- **Request Body**:
    ```json
    {
    "name": "Lucas;
    "lastName": "Ortiz";
    "rol": "admin-general";
    }
    ```

#### 4. Actualizar Admin
- **URL**: `/admins/{id}`
- **Método**: `PUT`
- **Path Variables**:
    - `{id}` Long:id de admin
- **Request Body**:
    ```json
    {
    "name": "Lucas;
    "lastName": "Ortiz";
    "rol": "admin-junior";
    }
    ```
    
#### 5. Borrar Admin
- **URL**: `/admins/{id}`
- **Método**: `DELETE`
- **Path Variables**:
    - `{id}` Long:id de admin

#### 6. Obtener informe de uso en tiempo (con o sin pausas) y distancia de los scooters (FUNCIONALIDAD DE ENUNCIADO)
- **URL**: `/admins/scooter-usage-report?includePauses={includePauses}`
- **Método**: `GET`
- **Path Variables**:
    - `{includePauses}` boolean: includePauses
- **Response Body**: 
    ```json
          {
             "scooterName": "ScooterA";
             "totalEffectiveUsage": "1 hora y 25 minutos";
             "totalKm": 2800.00;
          } 
     ```

 #### 7.  Obtener el total y muestra el monto total de todas las de facturas dentro de un rango en un año entre un mes inicial y un mes final (FUNCIONALIDAD DE ENUNCIADO)
- **URL**: `admins/total-invoice-amount?year={year}&starMonth={startMonth}&endMonth={endMonth}`
- **Método**: `GET`
- **Path Variables**:
    - `{year}` : int
    - `{starMonth}` : int
    - `{endMonth}` : int
- **Response Body**: 
    ```json
          {
            1250000.00
          } 
     ```   

