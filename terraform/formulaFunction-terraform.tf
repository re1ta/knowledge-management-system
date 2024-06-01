resource yandex_function import_function {
  name               = "latex-calculator"
  user_hash          = uuid()
  folder_id          = "b1gm6rt0iq59gajrp4ot"
  description        = "Считает LaTeX функции"
  runtime            = "nodejs18"
  entrypoint         = "import.handler"
  memory             = 256
  execution_timeout  = 300
  content {
    zip_filename = "../function.zip"
  }
}