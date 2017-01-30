$.ajaxSetup({
    async: false
});

mainApp.controller('addPeriodCtrl', ['$scope', function ($scope) {

        /**
         * validation
         */
        $scope.submit = function (form, event) {

            $scope.invalid = false;
            if (form.$invalid) {
                $scope.invalid = true;
                event.preventDefault();
            }
        };

        /**
         * pagination
         
        $scope.offset = 1;
        $scope.pages = 0;
        $scope.getPagination = function (offset) {

            console.log("offset: " + offset);
            console.log("pages: " + $scope.pages);
            
            if (offset >= 1 && offset <=  $scope.pages) {
               
               $scope.offset = offset;
               $.ajax({
                    method: "POST",
                    url: "periods",
                    data: {submit: 'pagination', offset: offset},
                    async: false
               }).done(function (data) {

                    $("#periods").html(data);
                    $(".pages").removeClass("active");
                    $(".page-"+offset).addClass("active");
               });
               
               
            }

        };
        */
    }]);

