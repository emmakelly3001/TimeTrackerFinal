



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

// reflected XSS
        function redirectToReflectedXSS() {
            // Get the value of the input field
            const inputValue = document.getElementById('userInput').value;

            // Construct the URL with the input parameter
            const url = `http://localhost:8080/echo?input=${encodeURIComponent(inputValue)}`;

            // Redirect to the constructed URL
            window.location.href = url;
        }


        //Stored XSS
        // Function to fetch and display comments
        async function loadComments() {
            const response = await fetch('/comments'); // Call the backend API
            const comments = await response.json();


            const output = document.getElementById('commentOutput');
            output.innerHTML = '';

            comments.forEach(comment => {
                   //Creating the content safely, and append the safe comment to the output element
                   const commentElement = document.createElement('div');

                   //safely set the comment text using textcontent, which will escape any HTML tags.
                   commentElement.textContent = comment;
                   output.appendChild(commentElement);

                   //Insecure/unsafe code
                   //const userInput = document.getElementById('commentInput').value;
                   //document.getElementById('CommentOutput').innerHTML = "Hello " + comment;
                        //const scripts = document.getElementById('CommentOutput').getElementsByTagName('script');
                        //for (let i = 0; i < scripts.length; i++) {
                            //eval(scripts[i].innerHTML);
                        //}
            });
        }

        // Stored XSS - contd.
        async function submitComment() {
            const input = document.getElementById('commentInput').value;
            await fetch('/comments', {
                method: 'POST',
                headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
                body: `comment=${encodeURIComponent(input)}`
            });
            loadComments(); // Refresh the comment list
        }

        // Load comments on page load
        window.onload = loadComments;



            //XSS Dom-Based
            function displayInput() {
                   const userInput = document.getElementById('DomInput').value;

                   //Securely display user input by using textContent
                   const outputElement = document.getElementById('output');
                   outputElement.textContent = "Hello, " + userInput; //safely handle user input
            }