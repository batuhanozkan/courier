
# **Courier Tracking Application**

This application tracks couriers, logs their activities, and calculates the total distance traveled. It provides RESTful APIs for logging courier locations, retrieving courier activity logs, and calculating travel distances.

---

## **Features**

- Log courier locations and store entries.
- Calculate the total travel distance of a courier.
- Retrieve activity logs for specific couriers.
- Load store information from a JSON file.The data is loaded from the stores.json file located in the src/main/resources folder.
---

## **Prerequisites**

1. **Java 17+** installed on your system.
2. A tool like **Postman** or **cURL** for testing the APIs.

---

## **Database: H2 In-Memory Database**

The application uses **H2 in-memory database** for storing data. The H2 database is lightweight and allows you to quickly test the application without requiring an external database setup.

### **H2 Console Access**

Once the application is running, you can access the H2 database console at:  
[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

#### **H2 Console Configuration**

- **JDBC URL**: `jdbc:h2:mem:testdb`  
- **Username**: `sa`  
- **Password**: `pass` 

---

### **Database Tables**

The following tables are created in the H2 database:

1. **`courier_log`**  
   Stores the logs of couriers entering store locations.
   - `id` (Primary Key)
   - `courier_id`
   - `entry_time`
   - `store_name`

2. **`courier_history`**  
   Stores the complete history of courier movements.
   - `id` (Primary Key)
   - `courier_id`
   - `lat` (Latitude)
   - `lng` (Longitude)
   - `time` (Timestamp)

3. **`store`**  
   Stores information about store locations. The data is loaded from the stores.json file located in the src/main/resources folder.
   - `id` (Primary Key)
   - `name`
   - `lat` (Latitude)
   - `lng` (Longitude)

---

## **Getting Started**

### ** Run the Application**

Use the following command to run the application:

```bash
java -jar target/courier-0.0.1-SNAPSHOT.jar
```

---

## **Testing the APIs**

You can test the APIs using **Postman**, **cURL**, or any other API testing tool.

### **1. Log Courier Location**

**Endpoint:**  
`POST /courier/logCourier`

**Request Body (JSON):**
```json
{
  "courierId": 1,
  "time": "2025-01-03T14:30:00",
  "lat": 40.9923307,
  "lng": 29.1244229
}
```

**Example cURL Command:**
```bash
curl -X POST -H "Content-Type: application/json" -d '{"courierId":1,"time":"2025-01-03T14:30:00","lat":40.9923307,"lng":29.1244229}' http://localhost:8080/courier/logCourier
```

---

### **2. Get Total Travel Distance**

**Endpoint:**  
`GET /courier/getTotalTravelDistance/{courierId}`

**Example Request:**
```bash
curl -X GET http://localhost:8080/courier/getTotalTravelDistance/1
```

---

### **3. Get Courier Log**

**Endpoint:**  
`GET /courier/getCourierLog/{courierId}`

**Example Request:**
```bash
curl -X GET http://localhost:8080/courier/getCourierLog/1
```

---

