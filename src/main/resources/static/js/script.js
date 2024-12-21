// script.js

//document.getElementById('sendRequestBtn').addEventListener('click', function() {
// Listen for the form submit event
document.getElementById('registerForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission behavior

    // Get the form data
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    // Create an object to send as the request body
    const userData = {
        name: name,
        email: email,
        password: password
    };

    // Send the POST request to the backend endpoint
    fetch('http://localhost:8080/addUser', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData) // Convert the data to JSON format

    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to register user');
        }
        return response.json();  // If the response is successful, parse it as JSON
    })
    .then(data => {
        // Handle the success response
        document.getElementById('resultMessage').innerHTML = "User registered successfully with ID:" + data;
        // Optionally, clear the form
        document.getElementById('registerForm').reset();
    })
    .catch(error => {
        // Handle errors, such as network issues
        document.getElementById('resultMessage').innerHTML = "Error: " + error.message;
    });
});
