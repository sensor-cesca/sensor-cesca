angular.module('sensors', [])
.controller('Sensors', function($scope, $http, $interval) {
	var url = 'http://localhost:8080/REST/DHTSensor';
	console.log(url);
    
	
	$scope.loadSensors = function(){

		$http({
			method: 'GET',
			url: '/REST/DHTSensor',
			headers: {'Content-Type': 'application/json'}
		}).then(function successCallback(response){
			console.log("Loading Sensors Data");
			console.log(response.data);
			
			$scope.sensors = response.data;
			
			console.log(response.status);
		});
    }	
	
    $interval(function(){
    	$scope.loadSensors();
	    },5000);
});