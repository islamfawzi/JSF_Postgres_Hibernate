mainApp.controller('addProcessCtrl', ['$scope', function ($scope) {

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
}]);


