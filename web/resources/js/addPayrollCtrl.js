$.ajaxSetup({
    async: false
});

mainApp.controller('addPayrollCtrl', ['$scope', '$http', function ($scope, $http) {

        $scope.category = "";
        $scope.function = "";
        $scope.formula = "";
        $scope.payroll_items = [];
        $scope.validation = {};
        $scope.validation_class = "";


        /* validation */
        $scope.submit = function (form, event) {
            
            formula_validate();
            
            $scope.invalid = false;
            
            if (form.$invalid) {
                $scope.invalid = true;
                event.preventDefault();
            }
        };
        /* end validation */

        // watch the type
        $scope.$watch('type', function () {

            // if formula type
            if ($scope.type == 'for') {

                watchList();
            }
        });

        
        var formula_validate = function () {
            get('payrolls', {submit: 'validate-formula', formula: $scope.formula})
                    .success(function (data) {
                        $scope.validation = angular.fromJson(data);
                        console.log("message: " + $scope.validation.error_message);    
                        if (!$scope.validation.valid) {
                            $scope.validation_class = "not-valid";
                            $scope.invalid = true;
                        } else {
                            $scope.validation_class = "";
                        }
                    });
        }

        /**
         * watch $scope.list
         */
        var watchList = function () {

            // watch the list type
            $scope.$watch('list', function () {

                var url = 'payrolls';

                var submit = 'payroll-items-json';

                if ($scope.list == 'payroll') {

                    url = 'payrolls';
                    submit = 'payroll-items-json';

                } else if ($scope.list == 'employee-id') {

                    url = 'employees';
                    submit = 'employees-ids-json';

                } else if ($scope.list == 'employee-name') {

                    url = 'employees';
                    submit = 'employees-names-json';

                } else if ($scope.list == 'action') {

                    url = 'actions';
                    submit = 'actions-json';

                } else if ($scope.list == 'position') {

                    url = 'positions';
                    submit = 'positions-json';

                } else if ($scope.list == 'department') {

                    url = 'departments';
                    submit = 'departments-json';

                } else if ($scope.list == 'city') {

                    url = 'cities';
                    submit = 'cities-json';

                } else if ($scope.list == 'marital-status') {

                    url = 'marital-status';
                    submit = 'marital-status-json';

                } else if ($scope.list == 'military-status') {

                    url = 'military-status';
                    submit = 'military-status-json';

                } else if ($scope.list == 'degree') {

                    url = 'degrees';
                    submit = 'degrees-json';

                }

                get(url, {submit: submit})
                        .success(function (data) {
                            $scope.items = angular.fromJson(data);
                        });
            });
        };

        /* watchList() End */

        /**
         * HttpXmlRequest (Ajax) Post Requests
         * @param {type} url
         * @param {type} data
         * @returns $http
         */
        var get = function (url, data) {

            var params = $.param(data);
            return $http({
                method: 'POST',
                url: url,
                data: params,
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            });
        };

        /* get() End */


        $scope.addVar = function () {
            if ($scope.type == 'for' && $scope.vars.length > 0) {

                var variable = "{" + $scope.list + ":" + $scope.vars + "}";
                appendTo('#formula', variable);

                validate();
            }
        };

        $scope.addOperator = function () {
            if ($scope.type == 'for' && $scope.operator.length > 0) {

                appendTo('#formula', $scope.operator);

                validate();
            }
        };


        /* add function to formula textarea*/
        $scope.addFunction = function () {

            appendTo('#formula', $scope.function);

            validate();
        };

        /* end addFunction */

        /* append variables to textarea */
        var appendTo = function (element, value) {
            var cursorPos = $(element).prop('selectionStart');
            var v = $(element).val();
            var textBefore = v.substring(0, cursorPos);
            var textAfter = v.substring(cursorPos, v.length);

            $(element).val(textBefore + value + textAfter);

            $(element).focus();
            $(element).selectRange(cursorPos + String(value).length);
        }
        /* end appendTo */

        /* read json file */
        var getJson = function () {
            var obj;

            $.getJSON("assets/js/formula-functions.json", function (json) {
                obj = json;
            });
            return obj;
        }
        /* end getJson */

        /* get Categories */
        var getCats = function () {

            return Object.keys(getJson());
        }
        /* end getCats */

        $scope.json = getJson();
        $scope.categorys = getCats();
    }]);


$.fn.selectRange = function (start, end) {
    if (end === undefined) {
        end = start;
    }
    return this.each(function () {
        if ('selectionStart' in this) {
            this.selectionStart = start;
            this.selectionEnd = end;
        } else if (this.setSelectionRange) {
            this.setSelectionRange(start, end);
        } else if (this.createTextRange) {
            var range = this.createTextRange();
            range.collapse(true);
            range.moveEnd('character', end);
            range.moveStart('character', start);
            range.select();
        }
    });
};