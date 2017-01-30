
mainApp.controller('PayrollItemsSearchCtrl', ['$scope', '$http', '$q', function ($scope, $http, $q) {



        var Item = {
            title: "",
            type: "",
            catid: "",
        };

        $scope.$watch('Item', function () {
            console.log('form model has been changed');
            getPayrollItems();
        }, true);

        $scope.Item = Item;

        var getPayrollItems = function () {

            $.ajax({
                method: "POST",
                url: "payrolls",
                data: {
                    submit: 'search',
                    item: JSON.stringify(Item),
                },
                async: false
            }).done(function (data) {
                $("#payrollItems-checkboxes").html(data);
            });
        };

    }]);




