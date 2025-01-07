const API_BASE_URL = 'http://localhost:8080';

function showBlogs() {
    event.preventDefault();

    $.ajax({
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + getToken()
        },
        url: "http://localhost:8080/api/blogs",
        type: "GET",
        success: function (blogs) {
            let display = "";
            for (let i = 0; i < blogs.length; i++) {
                let blog = blogs[i];
                display += `<tr>
                <td>${blog.time}</td>
                <td>${blog.title}</td>
                <td>${blog.author}</td>
                <td>
                    <img width="250" height="150" src="${API_BASE_URL}/images/${blog.imageFile}" alt="No Image" class="img-thumbnail">
                </td>
                <td>
                    <a href="#" class="btn btn-warning btn-sm" onclick="update(${blog.id})">Update</a>
                    <a href="#" class="btn btn-danger btn-sm" onclick="delete(${blog.id})">Delete</a>
                </td>
                </tr>`;
            }
            $("#table-body").html(display);
        }
    })
}

function getToken() {
    return localStorage.getItem('token');
}