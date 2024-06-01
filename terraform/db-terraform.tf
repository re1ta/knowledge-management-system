resource "yandex_mdb_postgresql_cluster" "kms-claster" {
  name                = "kms-claster"
  environment         = "PRODUCTION"
  network_id          = yandex_vpc_network.kms_db_vpc.id
  deletion_protection = false

  host {
    zone             = "ru-central1-a"
    subnet_id        = yandex_vpc_subnet.kms_db_subnet.id
    assign_public_ip = true
  }

  config {
    version = 15
    resources {
      resource_preset_id = "s3-c2-m8"
      disk_type_id       = "network-ssd"
      disk_size          = 10
    }
  }
}

resource "yandex_mdb_postgresql_database" "kms-db" {
  cluster_id = yandex_mdb_postgresql_cluster.kms-claster.id
  name       = "kms-admin"
  owner      = "kms-admin"
  depends_on = [
  yandex_mdb_postgresql_user.kms-admin
  ]
}

resource "yandex_mdb_postgresql_user" "kms-admin" {
cluster_id = yandex_mdb_postgresql_cluster.kms-claster.id
name       = "kms-admin"
password   = "qweasdzxcr21062003"
}