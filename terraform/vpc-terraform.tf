resource "yandex_vpc_network" "kms_db_vpc" {
  folder_id = "b1gm6rt0iq59gajrp4ot"
  name        = "kms_vpc"
}

resource "yandex_vpc_subnet" "kms_db_subnet" {
  name           = "kms_vpc_a"
  description    = "for db"
  v4_cidr_blocks = ["192.168.0.0/24"]
  zone           = "ru-central1-a"
  network_id     = yandex_vpc_network.kms_db_vpc.id
  route_table_id = yandex_vpc_route_table.kms_db_rt.id
}

resource "yandex_vpc_route_table" "kms_db_rt" {
  name       = "rt-kms"
  network_id = yandex_vpc_network.kms_db_vpc.id
  static_route {
    destination_prefix = "0.0.0.0/0"
    next_hop_address   = "192.168.0.6"
  }
}

resource "yandex_vpc_network" "kms_public_vps" {
  folder_id = "b1gm6rt0iq59gajrp4ot"
  name      = "kms_public_vps"
}

resource "yandex_vpc_subnet" "kms_public_subnet" {
  folder_id      = "b1gm6rt0iq59gajrp4ot"
  name           = "kms_public_subnet"
  v4_cidr_blocks = ["10.20.30.0/24"]
  zone           = yandex_vpc_subnet.kms_db_subnet.zone
  network_id     = yandex_vpc_network.kms_public_vps.id
  route_table_id = yandex_vpc_route_table.kms_public_rt.id
}

resource "yandex_vpc_gateway" "nat_gateway" {
  folder_id      = "b1gm6rt0iq59gajrp4ot"
  name = "gateway"
  shared_egress_gateway {}
}

resource "yandex_vpc_route_table" "kms_public_rt" {
  folder_id      = "b1gm6rt0iq59gajrp4ot"
  name       = "kms_public_rt"
  network_id = yandex_vpc_network.kms_public_vps.id

  static_route {
    destination_prefix = "0.0.0.0/0"
    gateway_id         = yandex_vpc_gateway.nat_gateway.id
  }
}
