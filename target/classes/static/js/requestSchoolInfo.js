function requestSchoolInfo(url, data) {
  return new Promise(function (resolve, reject) {
    $.post(url, data, function (response) {
      resolve(response);
    }).fail(function (e) {
      resolve('');
    })
  });
}