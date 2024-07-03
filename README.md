# ThriftTreasures

## Overview
ThriftTreasures is a web application designed to enhance the online presence of thrift stores, enabling them to showcase and sell their unique items online. Built using Spring Boot, React, and MySQL, the application integrates secure payment processing through Stripe, ensuring a seamless and secure shopping experience for users.

## Features

### User Authentication and Authorization
- **Custom Authentication Context**: Implemented user authentication and authorization in React to ensure secure access and user-specific functionalities.
- **Secure User Orders**: Ensured that only authenticated users can place orders, enhancing security and user experience.

### Payment Integration
- **Stripe Integration**: Integrated Stripe for secure payment processing, allowing users to make payments with confidence.
- **Stripe Webhooks**: Utilized Stripe webhooks with ngrok to handle payment confirmations and trigger sales record updates in the MySQL database automatically for real-time payment processing.

### Technology Stack
- **Backend**: Spring Boot
- **Frontend**: React
- **Database**: MySQL
- **Payment Processing**: Stripe, Stripe Webhooks, ngrok

### Benefits
- **Enhanced Online Presence**: Helps thrift stores establish a robust online presence, driving more foot traffic to their physical locations.
- **Secure Payments**: Provides a secure platform for users to make payments, enhancing trust and reliability.
- **Real-time Payment Processing**: Ensures that payments are processed in real-time, keeping sales records up-to-date automatically.

### Deployment
- **Web Application**: Deployed on a reliable hosting platform, ensuring high availability and performance.

## How It Works
1. **User Registration**: Users register and log in to access the platform.
2. **Browse Products**: Users can browse items listed by various thrift stores.
3. **Place Orders**: Authenticated users can place orders and make payments using Stripe.
4. **Real-time Updates**: Stripe webhooks handle payment confirmations and update sales records in real-time.

## Getting Started
1. **Clone the Repository**: `git clone https://github.com/your-repo/thrifttreasures.git`
2. **Backend Setup**: 
   - Navigate to the backend directory: `cd backend`
   - Run the Spring Boot application: `mvn spring-boot:run`
3. **Frontend Setup**:
   - Navigate to the frontend directory: `cd frontend`
   - Install dependencies: `npm install`
   - Start the React application: `npm start`

## Contributing
- Contributions are welcome! Please submit pull requests for review.

## License
- This project is licensed under the MIT License.
