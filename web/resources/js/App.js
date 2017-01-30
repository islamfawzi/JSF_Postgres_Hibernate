
var mainApp = angular.module("MainApp", []);

mainApp.run(function ($rootScope) {

    $rootScope.getPagination = function (url, offset, pages, selector) {

        console.log("offset: " + offset);
        console.log("pages: " + pages);
        console.log("url: " + url);

        if (offset >= 1 && offset <= pages) {

            $.ajax({
                method: "POST",
                url: url,
                data: {submit: 'pagination', offset: offset},
                async: false
            }).done(function (data) {

                $(selector).html(data);
                $(".pages").removeClass("active");
                $(".page-" + offset).addClass("active");
               
            });


        } else {

            if (offset < 1) {
                offset = 1;
            } else if (offset > pages) {
                offset = pages;
            }
        }

        return offset;
    };
    
});