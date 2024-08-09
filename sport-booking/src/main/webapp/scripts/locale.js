document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("locales").addEventListener("change", function (name) {
        const selectedOption = this.value;
        if (selectedOption !== '') {
            let urlPart = window.location.href;
            console.log(urlPart);
            const pos = urlPart.indexOf('?lang');
            if (pos !== -1) {
                urlPart = urlPart.substring(0, pos);
            }
            else if (urlPart.indexOf('&lang') !== -1) {
                urlPart = urlPart.substring(0, urlPart.indexOf('&lang'));
            }
            const currentUrl = new URL(urlPart);
            currentUrl.searchParams.append("lang", selectedOption);
            window.location.replace(currentUrl);
        }
    });
});
