mainApp.controller('addDeptCtrl', ['$scope', function($scope){
     
        $scope.invalid = false;
        
        $scope.submit = function(form, event){
            
            if(form.$invalid){
                $scope.invalid = true;
                event.preventDefault();
            }
        };
        
}]);