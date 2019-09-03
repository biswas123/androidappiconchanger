var exec = require('cordova/exec');

exports.changeAppIcon = function (arg0, arg1, success, error) {
    exec(success, error, 'AndroidAppIconChanger', 'changeAppIcon', [arg0, arg1]);
};
