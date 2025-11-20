// Menú reoponsive
const toggle =
document.getElementById("toggle");
const menu =
document.querySelector(".nav-menu ul");

toggle.addEventListener("click", () => {
    menu.classList.toggle("active");
});

// Animacion inicial
window.addEventListener("load", () => {

    document.querySelector(".hero-content").classList.add("fade-in");
});
