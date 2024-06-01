resource "yandex_iam_service_account" "admin" {
  name        = "admin"
  folder_id   = "b1gm6rt0iq59gajrp4ot"
}

resource "yandex_iam_service_account_static_access_key" "kms_sa_static_key" {
  service_account_id = yandex_iam_service_account.admin.id
}