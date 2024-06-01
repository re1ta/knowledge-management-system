resource "yandex_container_registry" "kms-container-registry" {
  name      = "kms-container-registry"
  folder_id = "b1gm6rt0iq59gajrp4ot"
}

resource "yandex_serverless_container" "kms-container" {
  name               = "kms-container"
  memory             = 4096
  service_account_id = yandex_iam_service_account.admin.id
  image {
    url = ""
  }
}