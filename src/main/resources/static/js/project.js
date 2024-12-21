



    document.getElementById('projectForm').addEventListener('submit', function(event){
        event.preventDefault(); // Prevent default form submission

        const name = document.getElementById('projectSearch').value; // Get the value entered by the user
        console.log(name);

        // Send a POST request to /userLogin
        fetch('http://localhost:8080/searchProject', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify( {name: name })
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Invalid project query');
            }
        })
        .then(data => {
            console.log("Backend response:", data); // Log the response from the backend
        })
        .catch(error => {
            console.error('Error:', error);
            document.getElementById('errorMessage').textContent = error.message;
        });
    });
