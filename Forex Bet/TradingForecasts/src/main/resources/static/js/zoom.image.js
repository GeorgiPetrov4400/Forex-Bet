var images = document.querySelectorAll(".img_responsive");

images.forEach(function(image) {
    image.addEventListener("click", function() {
        image.style.transform = "scale(1.8);";
    });
});
