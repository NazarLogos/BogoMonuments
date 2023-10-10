function checkWindowSize() {
    if (window.innerWidth < 1000) {
        document.querySelector('footer').classList.add('fixed-bottom');
    } else {
        document.querySelector('footer').classList.remove('fixed-bottom');
    }
}

// Перевіряйте розмір вікна при завантаженні сторінки і при зміні розміру вікна
window.onload = checkWindowSize;
window.onresize = checkWindowSize;