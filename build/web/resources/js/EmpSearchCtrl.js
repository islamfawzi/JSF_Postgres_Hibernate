
mainApp.controller('EmpSearchCtrl', ['$scope', '$http', '$q', function ($scope, $http, $q) {



        var Emp = {
            emp_name: "",
            emp_id: "",
            emp_position: "",
            emp_dept: "",
            emp_hiringdate: "",
            emp_birthdate: "",
            emp_city: "",
            emp_degree: "",
            emp_marital: "",
            emp_military: "",
            emp_status: "-1"
        };

        $scope.Emp = Emp;

        $scope.$watch('Emp', function () {
            getEmps();
        }, true);

        var getEmps = function () {

            $.ajax({
                method: "POST",
                url: "employees",
                data: {
                    submit: 'search',
                    emp: JSON.stringify(Emp),
                },
                async: false
            }).done(function (data) {
                $("#employees_checkboxes").html(data);
            });
        };







    }]);




