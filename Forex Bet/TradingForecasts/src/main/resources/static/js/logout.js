function logout() {
    fetch('/users/logout', {
        method: 'POST'
    })
        .then(response => {
            if (response.ok) {
                window.location.href = '/';
            } else {
                alert('Logout failed. Please try again.');
            }
        });
}
