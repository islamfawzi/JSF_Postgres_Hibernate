mainApp.controller('addEmpCtrl', ['$scope', function ($scope) {

        $scope.submit = function (form, event) {

            $scope.invalid = false;
            $scope.invalid_fullname = false;
            $scope.invalid_empid = false;
            $scope.invalid_position = false;
            $scope.invalid_department = false;

            $scope.invalid_username = false;
            $scope.invalid_pwd = false;
            $scope.invalid_confpwd = false;
            $scope.invalid_matchpwd = false;

            if (form.emp_fullname.$invalid) {
                $scope.invalid_fullname = true;
                event.preventDefault();
            }
            if (form.emp_id.$invalid) {
                $scope.invalid_empid = true;
                event.preventDefault();
            }
            if (form.emp_position.$invalid) {
                $scope.invalid_position = true;
                event.preventDefault();
            }
            if (form.emp_department.$invalid) {
                $scope.invalid_department = true;
                event.preventDefault();
            }

            if (form.emp_department.$invalid) {
                $scope.invalid_department = true;
                event.preventDefault();
            }

            if ($scope.isUser) {
                if (form.emp_username.$invalid) {
                    $scope.invalid_username = true;
                    event.preventDefault();
                }
                if (form.emp_password.$invalid) {
                    $scope.invalid_pwd = true;
                    event.preventDefault();
                }
                if (form.emp_confirmpassword.$invalid) {
                    $scope.invalid_confpwd = true;
                    event.preventDefault();
                }

                if ($scope.emp_password != '' && $scope.emp_password != $scope.emp_confirmpassword) {
                    $scope.invalid_matchpwd = true;
                    event.preventDefault();
                }
            }
        };


        $scope.getPayrollItems = function () {

            console.log("getPayrollItems start");

            $.ajax({
                method: "POST",
                url: "payrolls",
                data: {submit: 'department-payroll-items', dept_id: $scope.emp_department},
                async: false
            }).done(function (data) {

                $("#payroll").html(data);
                
            }); /* ajax End */

        }; /* getPayrollItems End */



    }]);