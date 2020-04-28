function requestSchoolInfo(url, data) {
  return new Promise(function (resolve, reject) {
    $.post(url, data, function (reponse) {
      resolve(reponse);
    }).fail(function (e) {
      resolve('');
    })
  });
}