// login.js

document.getElementById('loginForm').addEventListener('submit', function(event) {
    event.preventDefault();  // Prevent form from submitting the default way

    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    // Create user object
    const user = {
        email: email,
        password: password
    };

    // Send a POST request to /userLogin
    fetch('http://localhost:8080/userLogin', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Invalid email or password');
        }
    })
    .then(data => {
        console.log("Backend response:", data); // Log the response from the backend

        // Check if the data is a boolean indicating login success/failure
            if (data === true) {
                console.log('Login successful');
                window.location.href = '../html/dashboard.html';  // Redirect to dashboard or home page

            } else {
                console.log('Login failed');
                document.getElementById('errorMessage').textContent = 'Invalid email or password';
            }
    })
    .catch(error => {
        console.error('Error:', error);
        document.getElementById('errorMessage').textContent = error.message;
        console.log("Backend response:", data); // Log the response from the backend

    });
});
