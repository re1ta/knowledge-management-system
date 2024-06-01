terraform {
  required_providers {
    yandex = {
      source = "yandex-cloud/yandex"
    }
  }
}

provider "yandex" {
  token  =  "y0_AgAAAAAUyF_eAATuwQAAAAD_G9yAAACwJVZdwXNNhok7Y82VXERY1pym-A"
  cloud_id  = "b1gogttvdu7586m1kn1v"
  folder_id = "b1gm6rt0iq59gajrp4ot"
  zone      = "ru-central1-a"
}


 