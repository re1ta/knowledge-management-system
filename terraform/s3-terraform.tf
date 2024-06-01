resource "yandex_storage_bucket" "brusnika-documentation" {

  access_key = yandex_iam_service_account_static_access_key.kms_sa_static_key.access_key
  secret_key = yandex_iam_service_account_static_access_key.kms_sa_static_key.secret_key
  bucket = "brusnika-documentation-bucket"
  max_size = 8192
  default_storage_class = "standard"
  anonymous_access_flags {
    read        = true
    list        = true
    config_read = true
  }
}