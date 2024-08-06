package uz.ilmiddin1701.viewmodelresourse.models

data class Resource<T>(var status: Status, var data: T? = null, var message: String? = null) {

    companion object {
        fun <T> success(data: T): Resource<T> {
            return Resource(Status.Success, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }
}
