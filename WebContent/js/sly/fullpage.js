
/*global Sly, console */
$(document).ready(function(){
	'use strict';
	var options = {
		scrollBy: 200,
		speed: 200,
		easing: 'easeOutExpo',
		scrollBar: '#scrollbar',
		dynamicHandle: 1,
		dragHandle: 1,
		clickBar: 1
	};
	var frame = new Sly('#frame', options);

	
	// Initiate frame
	frame.init();

	// Reload on resize
	$(window).on('resize', function () {
		frame.reload();
	});


}); 