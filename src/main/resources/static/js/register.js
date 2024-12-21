// register.js

// Listen for the form submit event
document.getElementById('registerForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent the default form submission behavior

    // Get the form data
    const userName = document.getElementById('userName').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    // Create an object to send as the request body
    const userData = {
        userName: userName,
        email: email,
        password: password
    };

    // Send the POST request to the backend
    fetch('http://localhost:8080/addUser', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(userData)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Failed to register user');
        }
        // If the response is JSON, parse it
        const contentType = response.headers.get("Content-Type");
        if (contentType && contentType.includes("application/json")) {
                return response.json();  // Parse as JSON
            } else {
                return response.text();  // If it's not JSON, return as text
        }
    })
    .then(data => {
        console.log("EMMA response:", data);
            // Check if the data is a boolean indicating login success/failure
                if (data === null || data === '') {
                    console.log('Already Exists ');
 document.getElementById('resultMessage').innerHTML =
            `<span class="error">Error: error.message</span>`;                } else {
                    document.getElementById('resultMessage').innerHTML =
                                `<span class="success">User registered successfully!</span>`;
                            // Optionally, clear the form
                            document.getElementById('registerForm').reset();
                            // Redirect to dashboard or home page after successful login
                            console.log('Registration successful');
                            window.location.href = '../html/login.html';  // Redirect to dashboard or home page

                }

    })
    .catch(error => {
        document.getElementById('resultMessage').innerHTML =
            `<span class="error">Error: ${error.message}</span>`;
    });
});
