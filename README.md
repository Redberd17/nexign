# nexign

How to run application:

1. Go to the directory with compose.yml file
2. Run "docker-compose up"
3. Enjoy the successful logs

Main test cases:
1. Create task by controller
   <img width="1066" alt="Снимок экрана 2024-11-17 в 00 05 18" src="https://github.com/user-attachments/assets/46c915c7-698b-42cb-8c3b-ea2275e0b433">
2. Validation input nodel from kafka
   <img width="1414" alt="Снимок экрана 2024-11-17 в 00 07 24" src="https://github.com/user-attachments/assets/bb7eedba-f5c4-47f1-a02d-dc2e28b1c1c8">
3. Get task by id
   <img width="1061" alt="Снимок экрана 2024-11-17 в 00 09 03" src="https://github.com/user-attachments/assets/835876f8-9342-4410-8e90-8a0c4b444257">
4. Get task by not existing id

   (Validion for exists task by id. If task does not exist - catch exception, send status OK with provided internal status code)
   <img width="1080" alt="Снимок экрана 2024-11-17 в 00 09 53" src="https://github.com/user-attachments/assets/d2147222-4d2c-4d4b-8d96-9b31386346a4">
6. Check the current tast status from DB
   <img width="996" alt="Снимок экрана 2024-11-17 в 00 13 57" src="https://github.com/user-attachments/assets/93088bbe-1089-4ee0-8f58-a6bde0af2712">
7. Check the current status from API by id
   <img width="1077" alt="Снимок экрана 2024-11-17 в 00 15 28" src="https://github.com/user-attachments/assets/1b7de67b-453e-4c7f-837a-9d7c4d06e3ae">
8. Instances for application
   <img width="1061" alt="Снимок экрана 2024-11-17 в 00 16 20" src="https://github.com/user-attachments/assets/3f334339-3819-44d3-b062-78972fa7e737">
