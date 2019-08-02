function getPreview(path) {
	var preview;
	var sym = "\\";
	if (path !== null && path != undefined) {
		var paths = path.split(sym);
		var fileName = paths[paths.length - 1];
		var sub = paths[paths.length - 2];
		preview = "/imgview/preview?path=" + sub + "&fileName=" + fileName;
	}
	return preview;
}